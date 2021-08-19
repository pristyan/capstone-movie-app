package com.aoc.capstone.data.source.local

import com.aoc.capstone.data.dao.MovieDao
import com.aoc.capstone.data.dao.TvDao
import com.aoc.capstone.db.AppDatabase
import com.aoc.capstone.model.db.MovieDbEntity
import com.aoc.capstone.model.db.TvDbEntity
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class AppLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
): AppLocalDataSource {

    private val movieDao: MovieDao
        get() = appDatabase.movieDao()

    private val tvDao: TvDao
        get() = appDatabase.tvDao()

    override suspend fun getMovies(): List<MovieDbEntity> {
        return movieDao.getAll()
    }

    override suspend fun getMovieById(id: Int): List<MovieDbEntity> {
        return movieDao.getById(id)
    }

    override suspend fun insertMovie(movieDbEntity: MovieDbEntity) {
        movieDao.insert(movieDbEntity)
    }

    override suspend fun deleteMovie(movieDbEntity: MovieDbEntity) {
        movieDao.delete(movieDbEntity)
    }

    override suspend fun getTvSeries(): List<TvDbEntity> {
        return tvDao.getAll()
    }

    override suspend fun getTvSeriesById(id: Int): List<TvDbEntity> {
        return tvDao.getById(id)
    }

    override suspend fun insertTvSeries(tvDbEntity: TvDbEntity) {
        tvDao.insert(tvDbEntity)
    }

    override suspend fun deleteTvSeries(tvDbEntity: TvDbEntity) {
        tvDao.delete(tvDbEntity)
    }


}