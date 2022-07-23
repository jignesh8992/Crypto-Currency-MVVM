package com.jdroid.cryptoapp.presentation.coin_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jdroid.cryptoapp.common.Resource
import com.jdroid.cryptoapp.common.hasInternetConnection
import com.jdroid.cryptoapp.domain.model.CoinDetail
import com.jdroid.cryptoapp.domain.usecases.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailsViewModel @Inject constructor(val app: Application, private val getCoinDetailUseCase: GetCoinDetailsUseCase) : AndroidViewModel(app) {

    private val _coin: MutableLiveData<Resource<CoinDetail>> = MutableLiveData()
    val coin: LiveData<Resource<CoinDetail>> = _coin

    fun getCoinDetails(coinId: String) = viewModelScope.launch {

        if (app.hasInternetConnection()) {
            getCoinDetailUseCase(coinId).collect() {
                _coin.postValue(it)
            }
        } else {
            _coin.postValue(Resource.NoNetworkConnectivity())
        }


    }

}