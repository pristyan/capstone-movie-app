package com.aoc.capstone.domain.movielist

import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.Result


/**
 * Created by Chandra.
 **/

interface MovieListUseCase {

    suspend fun getPopularMovieList(page: Int): Result<Pair<List<Movie>, Int>>

}