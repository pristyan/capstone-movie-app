package com.aoc.favorite.domain

import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.model.view.Movie
import com.aoc.capstone.model.view.Tv
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class FavoriteListInteractor @Inject constructor(
    private val appRepository: AppRepository
): FavoriteListUseCase {

    override suspend fun getFavoriteMovies(): List<Movie> {
        return appRepository.getFavoriteMovies().map { Movie(it) }
    }

    override suspend fun getFavoriteTvSeries(): List<Tv> {
        return appRepository.getFavoriteTvSeries().map { Tv(it) }
    }

}