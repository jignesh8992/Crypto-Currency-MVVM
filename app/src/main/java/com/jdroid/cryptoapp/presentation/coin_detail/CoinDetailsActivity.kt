package com.jdroid.cryptoapp.presentation.coin_detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.jdroid.cryptoapp.common.Resource
import  com.jdroid.cryptoapp.databinding.ActivityCoinDetailsBinding
import com.jdroid.cryptoapp.domain.model.CoinModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailsActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    private val viewModel: CoinDetailsViewModel by viewModels()
    private lateinit var mBinding: ActivityCoinDetailsBinding
    private lateinit var coinModel: CoinModel

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

        mBinding.apply {
            tvCoinName.text = coinModel.name
            val status = if (coinModel.isActive!!) "Active" else "DeActive"
            val statusColor = if (coinModel.isActive!!) Color.GREEN else Color.RED
            tvCoinStatus.text = status
            tvCoinStatus.setTextColor(statusColor)
        }

        viewModel.getCoinDetails(coinModel.id.toString())

        viewModel.coin.observe(this) { result ->
            when (result) {
                is Resource.NoNetworkConnectivity -> {
                    Log.i(TAG, "NoNetworkConnectivity")
                }
                is Resource.Loading -> {
                    Log.i(TAG, "Loading")
                }
                is Resource.Success -> {
                    Log.i(TAG, "Success totals -->" + result.data)
                    result.data?.let {
                        mBinding.tvCoinDetails.text = result.data.description.toString()
                    }
                }
                is Resource.Error -> {
                    Log.i(TAG, "Error: ${result.message}")
                }
            }
        }
    }
}