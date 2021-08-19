package com.aoc.capstone.domain.moviesearch

import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.Result


/**
 * Created by Chandra.
 **/

interface MovieSearchUseCase {

    suspend fun searchMovie(keyword: String, page: Int): Result<Pair<List<Movie>, Int>>

}