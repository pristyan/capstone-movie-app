package com.aoc.capstone.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by Chandra.
 **/
data class TvListEntity(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<TvEntity>?,

    @SerializedName("total_pages")
    val totalPage: Int?
)
