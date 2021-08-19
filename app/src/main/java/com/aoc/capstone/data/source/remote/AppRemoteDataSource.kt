package com.aoc.capstone.data.source.remote

import com.aoc.capstone.model.entity.MovieEntity
import com.aoc.capstone.model.entity.MovieListEntity
import com.aoc.capstone.model.entity.TvEntity
import com.aoc.capstone.model.entity.TvListEntity
import com.aoc.core.base.response.BaseResponse


/**
 * Created by Chandra.
 **/

interface AppRemoteDataSource {

    suspend fun getPopularMovieList(page: Int): BaseResponse<MovieListEntity>

    suspend fun getMovieDetail(id: Int): BaseResponse<MovieEntity>

    suspend fun searchMovie(keyword: String, page: Int): BaseResponse<MovieListEntity>

    suspend fun searchTv(keyword: String, page: Int): BaseResponse<TvListEntity>

    suspend fun getPopularTvList(page: Int): BaseResponse<TvListEntity>

    suspend fun getTvDetail(id: Int): BaseResponse<TvEntity>

}