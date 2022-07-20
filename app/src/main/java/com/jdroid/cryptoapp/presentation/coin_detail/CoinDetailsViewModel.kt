package com.jdroid.cryptoapp.presentation.coin_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdroid.cryptoapp.common.Resource
import com.jdroid.cryptoapp.domain.model.CoinDetail
import com.jdroid.cryptoapp.domain.usecases.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailsViewModel @Inject constructor(private val getCoinDetailUseCase: GetCoinDetailsUseCase) : ViewModel() {

    val coin: MutableLiveData<Resource<CoinDetail>> = MutableLiveData()

     fun getCoinDetails(coinId: String) = viewModelScope.launch {
        getCoinDetailUseCase(coinId).collect() {
            coin.postValue(it)
        }
    }

}