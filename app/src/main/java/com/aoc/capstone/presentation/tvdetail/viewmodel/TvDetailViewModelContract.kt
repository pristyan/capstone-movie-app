package com.aoc.capstone.presentation.tvdetail.viewmodel

import androidx.lifecycle.LiveData
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface TvDetailViewModelContract : BaseViewModelContract {

    val detailSuccess: LiveData<Tv>
    val detailError: LiveData<String>
    val isFavorite: LiveData<Boolean>

    fun getDetail(id: Int): Job

    fun isFavorite(id: Int): Job

    fun setFavorite(): Job

    fun deleteFavorite(): Job

}