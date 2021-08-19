package com.aoc.capstone.presentation.tvlist.viewmodel

import androidx.lifecycle.LiveData
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface TvListViewModelContract : BaseViewModelContract {

    val popularListSuccess: LiveData<List<Tv>>
    val popularListError: LiveData<String>

    fun getPopularTvList(isRefresh: Boolean): Job

}