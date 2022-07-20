package com.jdroid.cryptoapp.presentation.coin_list

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jdroid.cryptoapp.common.Resource
import  com.jdroid.cryptoapp.databinding.ActivityCoinListBinding
import com.jdroid.cryptoapp.presentation.coin_detail.CoinDetailsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinListActivity : AppCompatActivity() {


    private val TAG = javaClass.simpleName
    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var mBinding: ActivityCoinListBinding
    private lateinit var adapter: CoinListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCoinListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        adapter = CoinListAdapter()
        mBinding.rvCoinList.adapter = adapter

        adapter.setOnItemClickListener { coinModel ->
            startActivity(CoinDetailsActivity.launchIntent(this, coinModel))
            Log.i(TAG, "ItemClick--> " + coinModel.name)
        }

        viewModel.coins.observe(this) { result ->
            when (result) {
                is Resource.NoNetworkConnectivity -> {
                    Log.i(TAG, "NoNetworkConnectivity")
                }
                is Resource.Loading -> {
                    Log.i(TAG, "Loading")
                }
                is Resource.Success -> {
                    Log.i(TAG, "Success totals -->" + result.data?.size)
                    result.data?.let {
                        adapter.differ.submitList(it.take(15))
                    }

                }
                is Resource.Error -> {
                    Log.i(TAG, "Error: ${result.message}")
                }
            }
        }


    }
}