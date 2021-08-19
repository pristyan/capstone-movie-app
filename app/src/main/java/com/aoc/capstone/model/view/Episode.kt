package com.aoc.capstone.model.view

import com.aoc.capstone.model.entity.EpisodeEntity
import com.aoc.core.constant.NetworkConstant
import com.aoc.core.util.parseDate
import java.io.Serializable

data class Episode(
    val airDate: String,
    val episodeNumber: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val seasonNumber: Int,
    val stillPath: String,
    val voteAverage: Double,
    val voteCount: Int
) : Serializable {

    constructor(entity: EpisodeEntity?) : this(
        airDate = entity?.airDate.orEmpty(),
        episodeNumber = entity?.episodeNumber ?: 0,
        id = entity?.id ?: 0,
        name = entity?.name.orEmpty(),
        overview = entity?.overview.orEmpty(),
        productionCode = entity?.productionCode.orEmpty(),
        seasonNumber = entity?.seasonNumber ?: 0,
        stillPath = entity?.stillPath.orEmpty(),
        voteAverage = entity?.voteAverage ?: 0.0,
        voteCount = entity?.voteCount ?: 0,
    )

    constructor() : this(
        airDate = "",
        episodeNumber = 0,
        id = 0,
        name = "",
        overview = "",
        productionCode = "",
        seasonNumber = 0,
        stillPath = "",
        voteAverage = 0.0,
        voteCount = 0
    )

    val formattedEpisodeNumber: String
        get() = "Episode $episodeNumber"

    val formattedAirDate: String
        get() = "(${airDate.parseDate()})"

    val fullPosterUrl: String
        get() = NetworkConstant.IMG_BASE_URL + stillPath

}