package com.aoc.favorite.presentation.favoritelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aoc.capstone.model.view.Movie
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.viewmodel.BaseViewModel
import com.aoc.core.scheduler.SchedulerProvider
import com.aoc.favorite.domain.FavoriteListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class FavoriteListViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val favoriteListUseCase: FavoriteListUseCase
): BaseViewModel(), FavoriteListViewModelContract {

    private val _movieList = MutableLiveData<List<Movie>>()
    override val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _tvList = MutableLiveData<List<Tv>>()
    override val tvList: LiveData<List<Tv>>
        get() = _tvList

    override fun getMovies(): Job = launch(schedulerProvider.ui()) {
        val result = withContext(schedulerProvider.io()) {
            favoriteListUseCase.getFavoriteMovies()
        }

        _movieList.value = result
    }

    override fun getTvSeries(): Job = launch(schedulerProvider.ui()) {
        val result = withContext(schedulerProvider.io()) {
            favoriteListUseCase.getFavoriteTvSeries()
        }

        _tvList.value = result
    }
}