package com.jdroid.cryptoapp.data.remote

import com.jdroid.cryptoapp.data.dto.CoinDetailsDto
import com.jdroid.cryptoapp.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailsDto
}