package com.aoc.capstone.domain.moviesearch

import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.Result
import com.aoc.core.constant.NetworkConstant
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class MovieSearchInteractor @Inject constructor(
    private val appRepository: AppRepository
): MovieSearchUseCase {
    override suspend fun searchMovie(keyword: String, page: Int): Result<Pair<List<Movie>, Int>> {
        val response = appRepository.searchMovie(keyword, page)
        return if (response.isSuccessful) {
            val movies = response.data?.results?.map { Movie(it) }
            Result.Success(Pair(movies ?: emptyList(), response.data?.totalPage ?: 0))
        } else {
            response.throwable?.printStackTrace()
            Result.Error(response.throwable?.message ?: NetworkConstant.SOMETHING_WRONG)
        }
    }
}