package com.aoc.capstone.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by Chandra.
 **/
data class SpokenLanguageEntity(
    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("name")
    val name: String?
)
