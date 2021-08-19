package com.aoc.capstone.domain.tvdetail

import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.model.db.TvDbEntity
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.Result
import com.aoc.core.constant.NetworkConstant
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class TvDetailInteractor @Inject constructor(
    private val appRepository: AppRepository
): TvDetailUseCase {

    override suspend fun getTvDetail(id: Int): Result<Tv> {
        val response = appRepository.getTvDetail(id)
        return if (response.isSuccessful) {
            Result.Success(Tv(response.data))
        } else {
            Result.Error(response.throwable?.message ?: NetworkConstant.SOMETHING_WRONG)
        }
    }

    override suspend fun isFavoriteTvSeries(id: Int): Boolean {
        val response = appRepository.getFavoriteTvSeriesById(id)
        return response.isNotEmpty()
    }

    override suspend fun setFavorite(tv: Tv) {
        appRepository.insertFavoriteTvSeries(TvDbEntity(tv))
    }

    override suspend fun removeFromFavorite(tv: Tv) {
        appRepository.deleteFavoriteTvSeries(TvDbEntity(tv))
    }

}