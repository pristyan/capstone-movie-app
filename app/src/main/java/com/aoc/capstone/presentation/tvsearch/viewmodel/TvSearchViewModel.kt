package com.aoc.capstone.presentation.tvsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aoc.capstone.domain.tvsearch.TvSearchUseCase
import com.aoc.capstone.model.view.Tv
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

class TvSearchViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val tvSearchUseCase: TvSearchUseCase
) : BaseViewModel(), TvSearchViewModelContract {

    private val _listSuccess = MutableLiveData<List<Tv>>()
    override val listSuccess: LiveData<List<Tv>> = _listSuccess

    private val _listError = MutableLiveData<String>()
    override val listError: LiveData<String> = _listError

    private var currentPage = 1
    private var totalPage = 1

    private fun refreshPage() {
        currentPage = 1
        totalPage = 1
    }

    override fun searchTv(keyword: String, isRefresh: Boolean): Job =
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
                tvSearchUseCase.searchTv(keyword, currentPage)
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