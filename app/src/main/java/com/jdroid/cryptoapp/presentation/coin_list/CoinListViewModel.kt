package com.jdroid.cryptoapp.presentation.coin_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdroid.cryptoapp.common.Resource
import com.jdroid.cryptoapp.domain.model.CoinModel
import com.jdroid.cryptoapp.domain.usecases.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinListUseCase: GetCoinListUseCase) : ViewModel() {

    val coins: MutableLiveData<Resource<List<CoinModel>>> = MutableLiveData()

    init {
        getCoins()
    }

    fun getCoins() = viewModelScope.launch {
        getCoinListUseCase().collect() {
            coins.postValue(it)
        }
    }

}