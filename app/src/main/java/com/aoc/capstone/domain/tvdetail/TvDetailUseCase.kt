package com.aoc.capstone.domain.tvdetail

import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.Result


/**
 * Created by Chandra.
 **/

interface TvDetailUseCase {

    suspend fun getTvDetail(id: Int): Result<Tv>

    suspend fun isFavoriteTvSeries(id: Int): Boolean

    suspend fun setFavorite(tv: Tv)

    suspend fun removeFromFavorite(tv: Tv)
}