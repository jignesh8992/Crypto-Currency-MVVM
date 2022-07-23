package com.jdroid.cryptoapp.presentation.coin_list

import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jdroid.cryptoapp.R
import com.jdroid.cryptoapp.common.*
import com.jdroid.cryptoapp.common.callback.OnUserActionListener
import com.jdroid.cryptoapp.databinding.ActivityCoinListBinding
import com.jdroid.cryptoapp.domain.model.CoinModel
import com.jdroid.cryptoapp.presentation.coin_detail.CoinDetailsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinListActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var mBinding: ActivityCoinListBinding
    private lateinit var coinAdapter: CoinListAdapter
    private val coinList: ArrayList<CoinModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCoinListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        coinAdapter = CoinListAdapter()
        mBinding.rvCoinList.apply {
            adapter = coinAdapter
            addOnScrollListener(this@CoinListActivity.scrollListener)
            addItemDecoration(RvGridSpacingItemDecoration(1, 30, true))
        }

        coinAdapter.setOnItemClickListener { coinModel ->
            startActivity(CoinDetailsActivity.launchIntent(this, coinModel))
            Log.i(TAG, "ItemClick--> " + coinModel.name)
        }

        viewModel.coins.observe(this) { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.i(TAG, "Loading")
                    showProgress()
                }
                is Resource.NoNetworkConnectivity -> {
                    Log.i(TAG, "NoNetworkConnectivity")
                    dismissProgress()
                    showRetryAlert(getString(R.string.network_error), getString(R.string.you_are_offline))
                }
                is Resource.Success -> {
                    Log.i(TAG, "Success totals -->" + result.data?.size)
                    dismissProgress()
                    result.data?.let {
                        coinList.addAll(it)
                        coinAdapter.differ.submitList(coinList.take(QUERY_PAGE_SIZE))
                    }
                }
                is Resource.ServerError -> {
                    Log.i(TAG, "ServerError: ${result.message}")
                    dismissProgress()
                    showRetryAlert(getString(R.string.server_error), result.message)
                }

                is Resource.Error -> {
                    Log.i(TAG, "Error: ${result.message}")
                    dismissProgress()
                    showRetryAlert(getString(R.string.internal_error), result.message)
                }
            }
        }
    }

    private fun showRetryAlert(title: String? = null, errorMessage: String? = null) {
        showAlert(title, errorMessage, getString(R.string.button_retry), getString(R.string.button_cancel), object : OnUserActionListener {
            override fun onYes() {
                viewModel.getCoins()
            }
        }, true)
    }

    private fun showProgress() {
        isLoading = true
        mBinding.progressBar.visible
    }

    private fun dismissProgress() {
        isLoading = false
        mBinding.progressBar.gone
    }

    var isLoading = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = coinAdapter.itemCount != coinList.size
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE

            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                Log.i(TAG, "onScrolled: should paginate")
                val nextCoins = coinList.take(coinAdapter.itemCount + QUERY_PAGE_SIZE)
                coinAdapter.differ.submitList(nextCoins)
                isScrolling = false
            } else if (!isNotLoadingAndNotLastPage) {
                Log.i(TAG, "onScrolled: should not paginate")
            }
        }
    }

}