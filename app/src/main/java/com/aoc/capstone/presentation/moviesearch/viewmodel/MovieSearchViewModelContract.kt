package com.aoc.capstone.presentation.moviesearch.viewmodel

import androidx.lifecycle.LiveData
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface MovieSearchViewModelContract : BaseViewModelContract {

    val listSuccess: LiveData<List<Movie>>
    val listError: LiveData<String>

    fun searchMovie(keyword: String, isRefresh: Boolean): Job

}