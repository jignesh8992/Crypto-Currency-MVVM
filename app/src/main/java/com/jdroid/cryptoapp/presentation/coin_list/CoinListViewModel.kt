package com.jdroid.cryptoapp.presentation.coin_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jdroid.cryptoapp.common.Resource
import com.jdroid.cryptoapp.common.hasInternetConnection
import com.jdroid.cryptoapp.domain.model.CoinModel
import com.jdroid.cryptoapp.domain.usecases.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(val app: Application, private val getCoinListUseCase: GetCoinListUseCase) : AndroidViewModel(app) {

    private val _coins: MutableLiveData<Resource<List<CoinModel>>> = MutableLiveData()
    val coins: LiveData<Resource<List<CoinModel>>> = _coins

    init {
        getCoins()
    }

    fun getCoins() = viewModelScope.launch {
        if (app.hasInternetConnection()) {
            getCoinListUseCase().collect() {
                _coins.postValue(it)
            }
        } else {
            _coins.postValue(Resource.NoNetworkConnectivity())
        }
    }

}