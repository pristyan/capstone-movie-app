package com.aoc.capstone.presentation.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aoc.capstone.domain.moviedetail.MovieDetailUseCase
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

class MovieDetailViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val movieDetailUseCase: MovieDetailUseCase
) : BaseViewModel(), MovieDetailViewModelContract {

    private val _movieDetailSuccess = MutableLiveData<Movie>()
    override val movieDetailSuccess: LiveData<Movie> = _movieDetailSuccess

    private val _movieDetailError = MutableLiveData<String>()
    override val movieDetailError: LiveData<String> = _movieDetailError

    private val _isFavoriteMovie = MutableLiveData<Boolean>()
    override val isFavoriteMovie: LiveData<Boolean> = _isFavoriteMovie

    override fun getMovieDetail(id: Int): Job = launch(schedulerProvider.ui()) {
        setLoading(true)

        val result = withContext(schedulerProvider.io()) {
            movieDetailUseCase.getMovieDetail(id)
        }

        when (result) {
            is Result.Error -> _movieDetailError.value = result.message
            is Result.Success -> _movieDetailSuccess.value = result.data
        }

        setLoading(false)
    }

    override fun isFavorite(id: Int): Job = launch(schedulerProvider.ui()) {
        val result = withContext(schedulerProvider.io()) {
            movieDetailUseCase.isFavoriteMovie(id)
        }

        _isFavoriteMovie.value = result
    }

    override fun setFavorite(): Job = launch(schedulerProvider.ui()) {
        withContext(schedulerProvider.io()) {
            _movieDetailSuccess.value?.let { movieDetailUseCase.setFavorite(it) }
        }

        _isFavoriteMovie.value = true
    }

    override fun deleteFavorite(): Job = launch(schedulerProvider.ui()) {
        withContext(schedulerProvider.io()) {
            _movieDetailSuccess.value?.let { movieDetailUseCase.removeFromFavorite(it) }
        }

        _isFavoriteMovie.value = false
    }
}