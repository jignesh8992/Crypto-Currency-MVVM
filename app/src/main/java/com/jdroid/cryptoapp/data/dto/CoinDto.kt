package com.jdroid.cryptoapp.data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.jdroid.cryptoapp.domain.model.CoinModel
import kotlinx.coroutines.NonCancellable


@Keep
data class CoinDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("is_active") var isActive: Boolean? = null,
    @SerializedName("is_new") var isNew: Boolean? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("rank") var rank: Int? = null,
    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("type") var type: String? = null
)

