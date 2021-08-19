package com.aoc.capstone.presentation.moviesearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aoc.capstone.domain.moviesearch.MovieSearchUseCase
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.Result
import com.aoc.core.base.viewmodel.BaseViewModel
import com.aoc.core.scheduler.SchedulerProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class MovieSearchViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val movieSearchUseCase: MovieSearchUseCase
) : BaseViewModel(), MovieSearchViewModelContract {

    private val _listSuccess = MutableLiveData<List<Movie>>()
    override val listSuccess: LiveData<List<Movie>> = _listSuccess

    private val _listError = MutableLiveData<String>()
    override val listError: LiveData<String> = _listError

    private var currentPage = 1
    private var totalPage = 1

    private fun refreshPage() {
        currentPage = 1
        totalPage = 1
    }

    override fun searchMovie(keyword: String, isRefresh: Boolean): Job =
        launch(schedulerProvider.ui()) {
            when {
                isRefresh && keyword.isNotBlank() -> refreshPage()
                !isRefresh && currentPage < totalPage -> currentPage++
                keyword.isBlank() -> {
                    _listSuccess.value = emptyList()
                    setLoading(false)
                    return@launch
                }
                else -> return@launch
            }

            setLoading(true)

            val result = withContext(schedulerProvider.io()) {
                movieSearchUseCase.searchMovie(keyword, currentPage)
            }

            when (result) {
                is Result.Error -> _listError.value = result.message
                is Result.Success -> {
                    _listSuccess.value = result.data?.first ?: emptyList()
                    totalPage = result.data?.second ?: 1
                }
            }

            setLoading(false)
        }

}