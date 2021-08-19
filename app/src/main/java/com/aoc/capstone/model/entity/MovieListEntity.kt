package com.aoc.capstone.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by Chandra.
 **/
data class MovieListEntity(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<MovieEntity>?,

    @SerializedName("total_pages")
    val totalPage: Int?
)
