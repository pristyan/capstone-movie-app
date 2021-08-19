package com.aoc.capstone.domain.tvsearch

import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.Result


/**
 * Created by Chandra.
 **/

interface TvSearchUseCase {

    suspend fun searchTv(keyword: String, page: Int): Result<Pair<List<Tv>, Int>>
}