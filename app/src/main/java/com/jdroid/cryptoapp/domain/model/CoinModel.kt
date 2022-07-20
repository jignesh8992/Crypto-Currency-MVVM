package com.jdroid.cryptoapp.domain.model

import com.jdroid.cryptoapp.data.dto.CoinDto
import kotlinx.coroutines.NonCancellable
import java.io.Serializable

data class CoinModel(
    val id: String? = null,
    val isActive: Boolean? = null,
    val name: String? = null,
    val rank: Int? = null,
    val symbol: String? = null,
) : Serializable

fun CoinDto.toCoin(): CoinModel {
    return CoinModel(
        id = id,
        isActive = NonCancellable.isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}