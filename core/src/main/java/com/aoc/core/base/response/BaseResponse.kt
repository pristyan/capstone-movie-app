package com.aoc.core.base.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Chandra.
 **/
data class BaseResponse<T>(
    @SerializedName("data")
    val data: T? = null,

    @SerializedName("throwable")
    val throwable: Throwable? = null
) {

    val isSuccessful: Boolean
        get() = throwable == null

}