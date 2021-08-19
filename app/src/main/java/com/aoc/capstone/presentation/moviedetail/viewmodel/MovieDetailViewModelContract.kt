package com.aoc.capstone.presentation.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface MovieDetailViewModelContract : BaseViewModelContract {

    val movieDetailSuccess: LiveData<Movie>
    val movieDetailError: LiveData<String>
    val isFavoriteMovie: LiveData<Boolean>

    fun getMovieDetail(id: Int): Job

    fun isFavorite(id: Int): Job

    fun setFavorite(): Job

    fun deleteFavorite(): Job

}