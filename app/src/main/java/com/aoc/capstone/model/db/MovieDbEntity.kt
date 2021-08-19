package com.aoc.capstone.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aoc.capstone.model.view.Movie
import com.google.gson.annotations.SerializedName


/**
 * Created by Chandra.
 **/

@Entity(tableName = "movie")
data class MovieDbEntity(

    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String? = null

) {

    constructor(movie: Movie): this(
        id = movie.id,
        title = movie.title,
        overview = movie.overView,
        voteAverage = movie.rating,
        posterPath = movie.posterPath,
        releaseDate = movie.releaseDate
    )

}
