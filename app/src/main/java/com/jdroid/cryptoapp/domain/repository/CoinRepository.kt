package com.jdroid.cryptoapp.domain.repository

import com.jdroid.cryptoapp.data.dto.CoinDetailsDto
import com.jdroid.cryptoapp.data.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoin(coinId: String): CoinDetailsDto
}