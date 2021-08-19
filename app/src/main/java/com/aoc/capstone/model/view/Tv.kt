package com.aoc.capstone.model.view

import com.aoc.capstone.model.db.TvDbEntity
import com.aoc.capstone.model.entity.TvEntity
import com.aoc.core.constant.NetworkConstant
import com.aoc.core.util.parseDate
import java.io.Serializable


/**
 * Created by Chandra.
 **/

data class Tv(
    val backdropPath: String,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<Genre>,
    val productionCompanies: List<ProductionCompany>,
    val tagline: String,
    val numberOfEpisodes: Int,
    val lastAirDate: String,
    val lastEpisodeToAir: Episode,
    val nextEpisodeToAir: Episode
) : Serializable {

    constructor(entity: TvEntity?) : this(
        backdropPath = entity?.backdropPath.orEmpty(),
        firstAirDate = entity?.firstAirDate.orEmpty(),
        id = entity?.id ?: 0,
        name = entity?.name.orEmpty(),
        originalLanguage = entity?.originalLanguage.orEmpty(),
        originalName = entity?.originalName.orEmpty(),
        overview = entity?.overview.orEmpty(),
        popularity = entity?.popularity ?: 0.0,
        posterPath = entity?.posterPath.orEmpty(),
        voteAverage = entity?.voteAverage ?: 0.0,
        voteCount = entity?.voteCount ?: 0,
        genres = entity?.genres?.map { Genre(it) } ?: emptyList(),
        tagline = entity?.tagline.orEmpty(),
        numberOfEpisodes = entity?.numberOfEpisodes ?: 0,
        lastAirDate = entity?.lastAirDate.orEmpty(),
        lastEpisodeToAir = Episode(entity?.lastEpisodeToAir),
        nextEpisodeToAir = Episode(entity?.nextEpisodeToAir),
        productionCompanies = entity?.productionCompanies?.map {
            ProductionCompany(it)
        } ?: emptyList()
    )

    constructor(entity: TvDbEntity?) : this(
        id = entity?.id ?: 0,
        name = entity?.name.orEmpty(),
        overview = entity?.overview.orEmpty(),
        posterPath = entity?.posterPath.orEmpty(),
        voteAverage = entity?.voteAverage ?: 0.0,
        firstAirDate = "",
        originalLanguage = "",
        originalName = "",
        backdropPath = "",
        popularity = 0.0,
        voteCount = 0,
        genres = emptyList(),
        tagline = "",
        numberOfEpisodes = 0,
        lastAirDate = "",
        lastEpisodeToAir = Episode(),
        nextEpisodeToAir = Episode(),
        productionCompanies = emptyList()
    )

    val fullPosterUrl: String
        get() = NetworkConstant.IMG_BASE_URL + posterPath

    val parsedFirstAirDate: String
        get() = firstAirDate.parseDate()

}