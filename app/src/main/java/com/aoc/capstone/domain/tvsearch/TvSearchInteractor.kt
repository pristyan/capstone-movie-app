package com.aoc.capstone.domain.tvsearch

import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.Result
import com.aoc.core.constant.NetworkConstant
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class TvSearchInteractor @Inject constructor(
    private val appRepository: AppRepository
): TvSearchUseCase {

    override suspend fun searchTv(keyword: String, page: Int): Result<Pair<List<Tv>, Int>> {
        val response = appRepository.searchTv(keyword, page)
        return if (response.isSuccessful) {
            val movies = response.data?.results?.map { Tv(it) }
            Result.Success(Pair(movies ?: emptyList(), response.data?.totalPage ?: 0))
        } else {
            response.throwable?.printStackTrace()
            Result.Error(response.throwable?.message ?: NetworkConstant.SOMETHING_WRONG)
        }
    }
}