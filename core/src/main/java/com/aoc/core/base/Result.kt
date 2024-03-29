package com.aoc.core.base


/**
 * Created by Chandra.
 **/
sealed class Result<out T: Any> {

    class Success<out T: Any>(val data: T?): Result<T>()

    class Error(val message: String): Result<Nothing>()

}
