package com.aoc.favorite.presentation.favoritelist.viewmodel

import androidx.lifecycle.LiveData
import com.aoc.capstone.model.view.Movie
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface FavoriteListViewModelContract : BaseViewModelContract {

    val movieList: LiveData<List<Movie>>

    val tvList: LiveData<List<Tv>>

    fun getMovies(): Job

    fun getTvSeries(): Job

}