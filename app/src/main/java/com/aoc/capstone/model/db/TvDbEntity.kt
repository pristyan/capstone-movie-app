package com.aoc.capstone.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aoc.capstone.model.view.Tv
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "tv")
data class TvDbEntity(

    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double?

) : Serializable {

    constructor(data: Tv): this(
        id = data.id,
        name = data.name,
        overview = data.overview,
        posterPath = data.posterPath,
        voteAverage = data.voteAverage
    )

}