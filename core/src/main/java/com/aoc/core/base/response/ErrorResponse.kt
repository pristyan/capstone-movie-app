package com.aoc.core.base.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Chandra.
 **/

data class ErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int?,

    @SerializedName("status_message")
    val statusMessage: String?,

    @SerializedName("success")
    val success: Boolean?
)