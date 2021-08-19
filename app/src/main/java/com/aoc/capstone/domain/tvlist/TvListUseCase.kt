package com.aoc.capstone.domain.tvlist

import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.Result


/**
 * Created by Chandra.
 **/

interface TvListUseCase {

    suspend fun getPopularTvList(page: Int): Result<Pair<List<Tv>, Int>>

}