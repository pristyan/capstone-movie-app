package com.aoc.capstone.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by Chandra.
 **/

data class MovieEntity(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null,

    @SerializedName("budget")
    val budget: Int? = null,

    @SerializedName("genres")
    val genres: List<GenreEntity>? = null,

    @SerializedName("homepage")
    val homepage: String? = null,

    @SerializedName("imdb_id")
    val imdbId: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyEntity>? = null,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryEntity>? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("revenue")
    val revenue: Int? = null,

    @SerializedName("runtime")
    val runtime: Int? = null,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageEntity>? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("tagline")
    val tagline: String? = null,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null

)
