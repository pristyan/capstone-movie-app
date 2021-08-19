package com.aoc.core.util

import com.aoc.core.base.response.BaseResponse
import com.aoc.core.base.response.ErrorResponse
import com.aoc.core.constant.NetworkConstant
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Chandra.
 **/
inline fun <reified T> JsonObject.parseResponse(gson: Gson): BaseResponse<T> {
    return try {
        val entity = gson.fromJson(this, T::class.java)
        BaseResponse(data = entity)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        BaseResponse(data = null, throwable = Exception(NetworkConstant.PARSING_ERROR))
    }
}

fun <T> Throwable.parseErrorResponse(gson: Gson): BaseResponse<T> {
    this.printStackTrace()
    return when (this) {
        is HttpException -> {
            when (this.response()?.code() ?: 0) {
                in 400..451 -> {
                    val body = this.response()?.errorBody()?.string()
                    if (body != null) {
                        val response = gson.fromJson(body, ErrorResponse::class.java)
                        BaseResponse(throwable = Exception(response.statusMessage))
                    } else {
                        BaseResponse(throwable = Exception(NetworkConstant.SOMETHING_WRONG))
                    }
                }
                NetworkConstant.CODE_HTTP_500 -> {
                    BaseResponse(throwable = Exception(NetworkConstant.INTERNAL_ERROR))
                }
                NetworkConstant.CODE_NO_INTERNET -> {
                    BaseResponse(throwable = Exception(NetworkConstant.NO_CONNECTION_INTERNET))
                }
                else -> {
                    BaseResponse(throwable = Exception(NetworkConstant.SOMETHING_WRONG))
                }
            }
        }

        is IOException -> {
            BaseResponse(throwable = Exception(NetworkConstant.NO_CONNECTION_INTERNET))
        }

        is JsonParseException -> {
            BaseResponse(throwable = Exception(NetworkConstant.SOMETHING_WRONG))
        }

        else -> {
            BaseResponse(throwable = Exception(NetworkConstant.SOMETHING_WRONG))
        }
    }
}