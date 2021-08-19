package com.aoc.capstone.model.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "tv")
data class TvEntity(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_name")
    val originalName: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null,

    @SerializedName("genres")
    val genres: List<GenreEntity>? = null,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyEntity>? = null,

    @SerializedName("tagline")
    val tagline: String? = null,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @SerializedName("last_air_date")
    val lastAirDate: String? = null,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeEntity? = null,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeEntity? = null
) : Serializable