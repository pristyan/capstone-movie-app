package com.aoc.capstone.presentation.moviedetail.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.domain.moviedetail.MovieDetailInteractor
import com.aoc.capstone.domain.moviedetail.MovieDetailUseCase
import com.aoc.capstone.presentation.moviedetail.viewmodel.MovieDetailViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module(includes = [BaseFeatureModule::class])
interface MovieDetailModule {

    @Binds
    fun bindViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    fun bindMovieDetailUseCase(movieDetailInteractor: MovieDetailInteractor): MovieDetailUseCase

}