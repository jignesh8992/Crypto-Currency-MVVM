package com.jdroid.cryptoapp.presentation.coin_detail


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jdroid.cryptoapp.R
import com.jdroid.cryptoapp.common.Resource
import com.jdroid.cryptoapp.common.callback.OnUserActionListener
import com.jdroid.cryptoapp.common.gone
import com.jdroid.cryptoapp.common.showAlert
import com.jdroid.cryptoapp.common.visible
import com.jdroid.cryptoapp.databinding.ActivityCoinDetailsBinding
import com.jdroid.cryptoapp.domain.model.CoinDetail
import com.jdroid.cryptoapp.domain.model.CoinModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinDetailsActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    private val viewModel: CoinDetailsViewModel by viewModels()
    private lateinit var mBinding: ActivityCoinDetailsBinding
    private lateinit var coinModel: CoinModel
    private lateinit var teamMembersAdapter: TeamMembersAdapter

    companion object {
        fun launchIntent(mContext: Context, coinModel: CoinModel): Intent {
            val intent = Intent(mContext, CoinDetailsActivity::class.java)
            intent.putExtra("coin", coinModel)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCoinDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        coinModel = intent.getSerializableExtra("coin") as CoinModel

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = coinModel.name
        }



        mBinding.apply {
            tvCoinName.text = coinModel.name
            val status = if (coinModel.isActive!!) "Active" else "DeActive"
            val statusColor = if (coinModel.isActive!!) Color.GREEN else Color.RED
            tvCoinStatus.text = status
            tvCoinStatus.setTextColor(statusColor)

            teamMembersAdapter = TeamMembersAdapter(listOf())
            rvCoinTeamMember.adapter = teamMembersAdapter
        }

        viewModel.getCoinDetails(coinModel.id.toString())

        viewModel.coin.observe(this) { result ->
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
                    Log.i(TAG, "Success totals -->" + result.data)
                    dismissProgress()
                    setCoinDetailResult(result)
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

    private fun setCoinDetailResult(result: Resource.Success<CoinDetail>) {
        result.data?.let {

            result.data.description?.let {
                if (it.isNotEmpty()) {
                    mBinding.tvCoinDetails.text = it
                    mBinding.tvCoinDetails.visible
                }
            } ?: {
                mBinding.tvCoinDetails.gone
            }

            result.data.team?.let {
                if (it.isNotEmpty()) {
                    teamMembersAdapter.team = it
                    mBinding.tvTeamMember.visible
                    mBinding.rvCoinTeamMember.visible
                }
            } ?: {
                mBinding.tvTeamMember.gone
                mBinding.rvCoinTeamMember.gone
            }
        }
    }

    private fun showRetryAlert(title: String? = null, errorMessage: String? = null) {
        showAlert(title, errorMessage, getString(com.jdroid.cryptoapp.R.string.button_retry), getString(com.jdroid.cryptoapp.R.string.button_cancel), object : OnUserActionListener {
            override fun onYes() {
                viewModel.getCoinDetails(coinModel.id.toString())
            }

            override fun onNo() {
                super.onNo()
                onBackPressed()
            }
        }, true)
    }

    private fun showProgress() {
        mBinding.progressBar.visible
    }

    private fun dismissProgress() {
        mBinding.progressBar.gone
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}