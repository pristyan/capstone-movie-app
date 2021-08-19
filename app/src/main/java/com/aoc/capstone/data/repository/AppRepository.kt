package com.aoc.capstone.data.repository

import com.aoc.capstone.model.db.MovieDbEntity
import com.aoc.capstone.model.db.TvDbEntity
import com.aoc.capstone.model.entity.MovieEntity
import com.aoc.capstone.model.entity.MovieListEntity
import com.aoc.capstone.model.entity.TvEntity
import com.aoc.capstone.model.entity.TvListEntity
import com.aoc.core.base.response.BaseResponse


/**
 * Created by Chandra.
 **/

interface AppRepository {

    // Movie
    suspend fun getPopularMovieList(page: Int): BaseResponse<MovieListEntity>

    suspend fun getMovieDetail(id: Int): BaseResponse<MovieEntity>

    // Tv
    suspend fun getPopularTvList(page: Int): BaseResponse<TvListEntity>

    suspend fun getTvDetail(id: Int): BaseResponse<TvEntity>

    // Search
    suspend fun searchMovie(keyword: String, page: Int): BaseResponse<MovieListEntity>

    suspend fun searchTv(keyword: String, page: Int): BaseResponse<TvListEntity>

    // Favorite Movie
    suspend fun getFavoriteMovies(): List<MovieDbEntity>

    suspend fun getFavoriteMovieById(id: Int): List<MovieDbEntity>

    suspend fun insertFavoriteMovie(movieEntity: MovieDbEntity)

    suspend fun deleteFavoriteMovie(movieEntity: MovieDbEntity)

    // Favorite Tv
    suspend fun getFavoriteTvSeries(): List<TvDbEntity>

    suspend fun getFavoriteTvSeriesById(id: Int): List<TvDbEntity>

    suspend fun insertFavoriteTvSeries(tvEntity: TvDbEntity)

    suspend fun deleteFavoriteTvSeries(tvEntity: TvDbEntity)

}