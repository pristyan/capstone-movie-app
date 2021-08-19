package com.aoc.capstone.model.view

import com.aoc.capstone.model.db.MovieDbEntity
import com.aoc.capstone.model.entity.MovieEntity
import com.aoc.core.constant.NetworkConstant
import com.aoc.core.util.parseDate
import java.io.Serializable


/**
 * Created by Chandra.
 **/
data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overView: String,
    val rating: Double,
    val releaseDate: String,
    val tagline: String,
    val genres: List<Genre>,
    val productionCompanies: List<ProductionCompany>
): Serializable {

    constructor(entity: MovieEntity?): this(
        id = entity?.id ?: 0,
        title = entity?.title.orEmpty(),
        posterPath = entity?.posterPath.orEmpty(),
        overView = entity?.overview.orEmpty(),
        rating = entity?.voteAverage ?: 0.0,
        releaseDate = entity?.releaseDate.orEmpty(),
        tagline = entity?.tagline.orEmpty(),
        genres = entity?.genres?.map { Genre(it) } ?: emptyList(),
        productionCompanies = entity?.productionCompanies?.map {
            ProductionCompany(it)
        } ?: emptyList()
    )

    constructor(entity: MovieDbEntity?): this(
        id = entity?.id ?: 0,
        title = entity?.title.orEmpty(),
        posterPath = entity?.posterPath.orEmpty(),
        overView = entity?.overview.orEmpty(),
        rating = entity?.voteAverage ?: 0.0,
        releaseDate = entity?.releaseDate.orEmpty(),
        tagline = "",
        genres = emptyList(),
        productionCompanies = emptyList()
    )

    val fullPosterUrl: String
        get() = NetworkConstant.IMG_BASE_URL + posterPath

    val parsedReleaseDate: String
        get() = releaseDate.parseDate()

}
