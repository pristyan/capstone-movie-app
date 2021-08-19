package com.aoc.capstone.domain.movielist

import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.Result
import com.aoc.core.constant.NetworkConstant
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class MovieListInteractor @Inject constructor(
    private val appRepository: AppRepository
): MovieListUseCase {

    override suspend fun getPopularMovieList(page: Int): Result<Pair<List<Movie>, Int>> {
        val response = appRepository.getPopularMovieList(page)
        return if (response.isSuccessful) {
            val movies = response.data?.results?.map { Movie(it) }
            Result.Success(Pair(movies ?: emptyList(), response.data?.totalPage ?: 0))
        } else {
            response.throwable?.printStackTrace()
            Result.Error(response.throwable?.message ?: NetworkConstant.SOMETHING_WRONG)
        }
    }

}