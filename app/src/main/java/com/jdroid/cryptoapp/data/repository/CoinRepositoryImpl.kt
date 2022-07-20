package com.jdroid.cryptoapp.data.repository

import com.jdroid.cryptoapp.data.dto.CoinDetailsDto
import com.jdroid.cryptoapp.data.dto.CoinDto
import com.jdroid.cryptoapp.data.remote.CoinApi
import com.jdroid.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinApi) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoin(coinId: String): CoinDetailsDto {
        return api.getCoin(coinId)
    }

}