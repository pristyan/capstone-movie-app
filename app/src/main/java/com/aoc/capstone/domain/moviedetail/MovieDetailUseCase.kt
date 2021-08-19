package com.aoc.capstone.domain.moviedetail

import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.Result


/**
 * Created by Chandra.
 **/

interface MovieDetailUseCase {

    suspend fun getMovieDetail(id: Int): Result<Movie>

    suspend fun isFavoriteMovie(id: Int): Boolean

    suspend fun setFavorite(movie: Movie)

    suspend fun removeFromFavorite(movie: Movie)

}