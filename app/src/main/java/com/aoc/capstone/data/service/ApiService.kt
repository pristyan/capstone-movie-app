package com.aoc.capstone.data.service

import com.aoc.core.constant.NetworkConstant
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Chandra.
 **/

interface ApiService {

    @GET("${NetworkConstant.MOVIE_ENDPOINT_PREFIX}/popular")
    suspend fun getPopularMovieList(@Query("page") page: Int): JsonObject

    @GET("${NetworkConstant.MOVIE_ENDPOINT_PREFIX}/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): JsonObject

    @GET("${NetworkConstant.TV_ENDPOINT_PREFIX}/popular")
    suspend fun getPopularTvList(@Query("page") page: Int): JsonObject

    @GET("${NetworkConstant.TV_ENDPOINT_PREFIX}/{id}")
    suspend fun getTvDetail(@Path("id") id: Int): JsonObject

    @GET("${NetworkConstant.SEARCH_ENDPOINT_PREFIX}/movie")
    suspend fun searchMovie(
        @Query("query") keyword: String,
        @Query("page") page: Int
    ): JsonObject

    @GET("${NetworkConstant.SEARCH_ENDPOINT_PREFIX}/tv")
    suspend fun searchTv(
        @Query("query") keyword: String,
        @Query("page") page: Int
    ): JsonObject
}