package com.aoc.capstone.data.source.remote

import com.aoc.capstone.model.entity.MovieEntity
import com.aoc.capstone.data.service.ApiService
import com.aoc.capstone.model.entity.MovieListEntity
import com.aoc.capstone.model.entity.TvEntity
import com.aoc.capstone.model.entity.TvListEntity
import com.aoc.core.base.response.BaseResponse
import com.aoc.core.util.parseErrorResponse
import com.aoc.core.util.parseResponse
import com.google.gson.Gson
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class AppRemoteDataSourceImpl @Inject constructor(
    private val gson: Gson,
    private val apiService: ApiService
): AppRemoteDataSource {

    override suspend fun getPopularMovieList(page: Int): BaseResponse<MovieListEntity> {
        return try {
            apiService.getPopularMovieList(page).parseResponse(gson)
        } catch (throwable: Throwable) {
            throwable.parseErrorResponse(gson)
        }
    }

    override suspend fun getMovieDetail(id: Int): BaseResponse<MovieEntity> {
        return try {
            apiService.getMovieDetail(id).parseResponse(gson)
        } catch (throwable: Throwable) {
            throwable.parseErrorResponse(gson)
        }
    }

    override suspend fun searchMovie(keyword: String, page: Int): BaseResponse<MovieListEntity> {
        return try {
            apiService.searchMovie(keyword, page).parseResponse(gson)
        } catch (throwable: Throwable) {
            throwable.parseErrorResponse(gson)
        }
    }

    override suspend fun searchTv(keyword: String, page: Int): BaseResponse<TvListEntity> {
        return try {
            apiService.searchTv(keyword, page).parseResponse(gson)
        } catch (throwable: Throwable) {
            throwable.parseErrorResponse(gson)
        }
    }

    override suspend fun getPopularTvList(page: Int): BaseResponse<TvListEntity> {
        return try {
            apiService.getPopularTvList(page).parseResponse(gson)
        } catch (throwable: Throwable) {
            throwable.parseErrorResponse(gson)
        }
    }

    override suspend fun getTvDetail(id: Int): BaseResponse<TvEntity> {
        return try {
            apiService .getTvDetail(id).parseResponse(gson)
        } catch (throwable: Throwable) {
            throwable.parseErrorResponse(gson)
        }
    }

}