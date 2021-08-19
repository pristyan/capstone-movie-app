package com.aoc.capstone.domain.moviedetail

import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.model.db.MovieDbEntity
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.Result
import com.aoc.core.constant.NetworkConstant
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class MovieDetailInteractor @Inject constructor(
    private val appRepository: AppRepository
): MovieDetailUseCase {

    override suspend fun getMovieDetail(id: Int): Result<Movie> {
        val response = appRepository.getMovieDetail(id)
        return if (response.isSuccessful) {
            Result.Success(Movie(response.data))
        } else {
            Result.Error(response.throwable?.message ?: NetworkConstant.SOMETHING_WRONG)
        }
    }

    override suspend fun isFavoriteMovie(id: Int): Boolean {
        val response = appRepository.getFavoriteMovieById(id)
        return response.isNotEmpty()
    }

    override suspend fun setFavorite(movie: Movie) {
        appRepository.insertFavoriteMovie(MovieDbEntity(movie))
    }

    override suspend fun removeFromFavorite(movie: Movie) {
        appRepository.deleteFavoriteMovie(MovieDbEntity(movie))
    }

}