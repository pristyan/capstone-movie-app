package com.aoc.core.base.response

/**
 * Created by Chandra.
 **/
data class BaseResponse<T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {

    val isSuccessful: Boolean
        get() = throwable == null

}