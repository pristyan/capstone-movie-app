package com.aoc.capstone.presentation.movielist.viewmodel

import androidx.lifecycle.LiveData
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface MovieListViewModelContract : BaseViewModelContract {

    val popularListSuccess: LiveData<List<Movie>>
    val popularListError: LiveData<String>

    fun getPopularMovieList(isRefresh: Boolean): Job

}