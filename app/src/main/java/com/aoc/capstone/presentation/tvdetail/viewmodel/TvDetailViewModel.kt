package com.aoc.capstone.presentation.tvdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aoc.capstone.domain.tvdetail.TvDetailUseCase
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

class TvDetailViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val tvDetailUseCase: TvDetailUseCase
) : BaseViewModel(), TvDetailViewModelContract {

    private val _detailSuccess = MutableLiveData<Tv>()
    override val detailSuccess: LiveData<Tv> = _detailSuccess

    private val _detailError = MutableLiveData<String>()
    override val detailError: LiveData<String> = _detailError

    private val _isFavorite = MutableLiveData<Boolean>()
    override val isFavorite: LiveData<Boolean> = _isFavorite

    override fun getDetail(id: Int): Job = launch(schedulerProvider.ui()) {
        setLoading(true)

        val result = withContext(schedulerProvider.io()) {
            tvDetailUseCase.getTvDetail(id)
        }

        when (result) {
            is Result.Error -> _detailError.value = result.message
            is Result.Success -> _detailSuccess.value = result.data
        }

        setLoading(false)
    }

    override fun isFavorite(id: Int): Job = launch(schedulerProvider.ui()) {
        val result = withContext(schedulerProvider.io()) {
            tvDetailUseCase.isFavoriteTvSeries(id)
        }

        _isFavorite.value = result
    }

    override fun setFavorite(): Job = launch(schedulerProvider.ui()) {
        withContext(schedulerProvider.io()) {
            _detailSuccess.value?.let { tvDetailUseCase.setFavorite(it) }
        }

        _isFavorite.value = true
    }

    override fun deleteFavorite(): Job = launch(schedulerProvider.ui()) {
        withContext(schedulerProvider.io()) {
            _detailSuccess.value?.let { tvDetailUseCase.removeFromFavorite(it) }
        }

        _isFavorite.value = false
    }
}