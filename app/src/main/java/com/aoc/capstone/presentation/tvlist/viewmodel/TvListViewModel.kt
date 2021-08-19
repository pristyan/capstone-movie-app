package com.aoc.capstone.presentation.tvlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aoc.capstone.domain.tvlist.TvListUseCase
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

class TvListViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val tvListUseCase: TvListUseCase
) : BaseViewModel(), TvListViewModelContract {

    private val _popularListSuccess: MutableLiveData<List<Tv>> by lazy { MutableLiveData<List<Tv>>() }
    override val popularListSuccess: LiveData<List<Tv>>
        get() = _popularListSuccess

    private val _popularListError: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    override val popularListError: LiveData<String>
        get() = _popularListError

    private var totalPage: Int = 1
    private var currentPage: Int = 1

    private fun refreshPage() {
        currentPage = 1
        totalPage = 1
    }

    override fun getPopularTvList(isRefresh: Boolean): Job = launch(schedulerProvider.ui()) {
        when {
            isRefresh -> refreshPage()
            !isRefresh && currentPage < totalPage -> currentPage++
            else -> return@launch
        }

        setLoading(true)

        val result = withContext(schedulerProvider.io()) {
            tvListUseCase.getPopularTvList(currentPage)
        }

        when (result) {
            is Result.Error -> {
                _popularListError.value = result.message
            }
            is Result.Success -> {
                totalPage = result.data?.second ?: 1
                _popularListSuccess.value = result.data?.first ?: emptyList()
            }
        }

        setLoading(false)
    }
}