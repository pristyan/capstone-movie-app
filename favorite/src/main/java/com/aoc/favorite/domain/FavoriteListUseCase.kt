package com.aoc.favorite.domain

import com.aoc.capstone.model.view.Movie
import com.aoc.capstone.model.view.Tv


/**
 * Created by Chandra.
 **/

interface FavoriteListUseCase {

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun getFavoriteTvSeries(): List<Tv>

}