package com.aoc.capstone.presentation.tvsearch.viewmodel

import androidx.lifecycle.LiveData
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface TvSearchViewModelContract : BaseViewModelContract {

    val listSuccess: LiveData<List<Tv>>
    val listError: LiveData<String>

    fun searchTv(keyword: String, isRefresh: Boolean): Job

}