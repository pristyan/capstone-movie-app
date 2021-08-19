package com.aoc.capstone.data.source.local

import com.aoc.capstone.model.db.MovieDbEntity
import com.aoc.capstone.model.db.TvDbEntity


/**
 * Created by Chandra.
 **/

interface AppLocalDataSource {
    /**
     * Movie
     **/
    suspend fun getMovies(): List<MovieDbEntity>

    suspend fun getMovieById(id: Int): List<MovieDbEntity>

    suspend fun insertMovie(movieDbEntity: MovieDbEntity)

    suspend fun deleteMovie(movieDbEntity: MovieDbEntity)


    /**
     * TV Series
     **/
    suspend fun getTvSeries(): List<TvDbEntity>

    suspend fun getTvSeriesById(id: Int): List<TvDbEntity>

    suspend fun insertTvSeries(tvDbEntity: TvDbEntity)

    suspend fun deleteTvSeries(tvDbEntity: TvDbEntity)

}