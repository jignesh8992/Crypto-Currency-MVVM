package com.jdroid.cryptoapp.data.dto


import androidx.annotation.Keep
import com.jdroid.cryptoapp.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName

@Keep
data class CoinDetailsDto(
    @SerializedName("description") var description: String? = null,
    @SerializedName("development_status") var developmentStatus: String? = null,
    @SerializedName("first_data_at") var firstDataAt: String? = null,
    @SerializedName("hardware_wallet") var hardwareWallet: Boolean? = null,
    @SerializedName("hash_algorithm") var hashAlgorithm: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("is_active") var isActive: Boolean? = null,
    @SerializedName("is_new") var isNew: Boolean? = null,
    @SerializedName("last_data_at") var lastDataAt: String? = null,
    @SerializedName("links") var links: Links? = null,
    @SerializedName("links_extended") var linksExtended: List<LinksExtended?>? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("open_source") var openSource: Boolean? = null,
    @SerializedName("org_structure") var orgStructure: String? = null,
    @SerializedName("proof_type") var proofType: String? = null,
    @SerializedName("rank") var rank: Int? = null,
    @SerializedName("started_at") var startedAt: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("tags") var tags: List<Tag> = ArrayList(),
    @SerializedName("team") var team: List<Team> = ArrayList(),
    @SerializedName("type") var type: String? = null,
    @SerializedName("whitepaper") var whitepaper: WhitePaper? = null
)


@Keep
data class Links(
    @SerializedName("explorer") var explorer: List<String?>? = null,
    @SerializedName("facebook") var facebook: List<String?>? = null,
    @SerializedName("reddit") var reddit: List<String?>? = null,
    @SerializedName("source_code") var sourceCode: List<String?>? = null,
    @SerializedName("website") var website: List<String?>? = null,
    @SerializedName("youtube") var youtube: List<String?>? = null
)

@Keep
data class LinksExtended(
    @SerializedName("stats") var stats: Stats? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null
) {
    @Keep
    data class Stats(
        @SerializedName("contributors") var contributors: Int? = null,
        @SerializedName("followers") var followers: Int? = null,
        @SerializedName("stars") var stars: Int? = null,
        @SerializedName("subscribers") var subscribers: Int? = null
    )
}

@Keep
data class Tag(
    @SerializedName("coin_counter") var coinCounter: Int? = null,
    @SerializedName("ico_counter") var icoCounter: Int? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null
)

@Keep
data class Team(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("position") var position: String? = null
)

@Keep
data class WhitePaper(
    @SerializedName("link") var link: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null
)

