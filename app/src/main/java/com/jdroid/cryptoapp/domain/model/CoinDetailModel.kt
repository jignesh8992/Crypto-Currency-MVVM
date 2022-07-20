package com.jdroid.cryptoapp.domain.model

import com.jdroid.cryptoapp.data.dto.CoinDetailsDto
import com.jdroid.cryptoapp.data.dto.Team

data class CoinDetail(
    val coinId: String? = null,
    val name: String? = null,
    val description: String? = null,
    val symbol: String? = null,
    val rank: Int? = null,
    val isActive: Boolean? = null,
    val tags: List<String?>? = null,
    val team: List<Team>? = null,
)

fun CoinDetailsDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team
    )
}