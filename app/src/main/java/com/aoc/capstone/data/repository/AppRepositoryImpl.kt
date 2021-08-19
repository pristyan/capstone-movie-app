package com.aoc.capstone.data.repository

import com.aoc.capstone.data.source.local.AppLocalDataSource
import com.aoc.capstone.data.source.remote.AppRemoteDataSource
import com.aoc.capstone.model.db.MovieDbEntity
import com.aoc.capstone.model.db.TvDbEntity
import com.aoc.capstone.model.entity.MovieEntity
import com.aoc.capstone.model.entity.MovieListEntity
import com.aoc.capstone.model.entity.TvEntity
import com.aoc.capstone.model.entity.TvListEntity
import com.aoc.core.base.response.BaseResponse
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class AppRepositoryImpl @Inject constructor(
    private val appRemoteDataSource: AppRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource
): AppRepository {

    override suspend fun getPopularMovieList(page: Int): BaseResponse<MovieListEntity> {
        return appRemoteDataSource.getPopularMovieList(page)
    }

    override suspend fun getMovieDetail(id: Int): BaseResponse<MovieEntity> {
        return appRemoteDataSource.getMovieDetail(id)
    }

    override suspend fun getPopularTvList(page: Int): BaseResponse<TvListEntity> {
        return appRemoteDataSource.getPopularTvList(page)
    }

    override suspend fun getTvDetail(id: Int): BaseResponse<TvEntity> {
        return appRemoteDataSource.getTvDetail(id)
    }

    override suspend fun searchMovie(keyword: String, page: Int): BaseResponse<MovieListEntity> {
        return appRemoteDataSource.searchMovie(keyword, page)
    }

    override suspend fun searchTv(keyword: String, page: Int): BaseResponse<TvListEntity> {
        return appRemoteDataSource.searchTv(keyword, page)
    }

    override suspend fun getFavoriteMovies(): List<MovieDbEntity> {
        return appLocalDataSource.getMovies()
    }

    override suspend fun getFavoriteMovieById(id: Int): List<MovieDbEntity> {
        return appLocalDataSource.getMovieById(id)
    }

    override suspend fun insertFavoriteMovie(movieEntity: MovieDbEntity) {
        appLocalDataSource.insertMovie(movieEntity)
    }

    override suspend fun deleteFavoriteMovie(movieEntity: MovieDbEntity) {
        appLocalDataSource.deleteMovie(movieEntity)
    }

    override suspend fun getFavoriteTvSeries(): List<TvDbEntity> {
        return appLocalDataSource.getTvSeries()
    }

    override suspend fun getFavoriteTvSeriesById(id: Int): List<TvDbEntity> {
        return appLocalDataSource.getTvSeriesById(id)
    }

    override suspend fun insertFavoriteTvSeries(tvEntity: TvDbEntity) {
        appLocalDataSource.insertTvSeries(tvEntity)
    }

    override suspend fun deleteFavoriteTvSeries(tvEntity: TvDbEntity) {
        appLocalDataSource.deleteTvSeries(tvEntity)
    }

}