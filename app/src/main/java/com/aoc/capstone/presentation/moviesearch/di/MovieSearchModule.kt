package com.aoc.capstone.presentation.moviesearch.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.domain.moviesearch.MovieSearchInteractor
import com.aoc.capstone.domain.moviesearch.MovieSearchUseCase
import com.aoc.capstone.presentation.moviesearch.viewmodel.MovieSearchViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module(includes = [BaseFeatureModule::class])
interface MovieSearchModule {

    @Binds
    fun bindViewModel(movieSearchViewModel: MovieSearchViewModel): ViewModel

    @Binds
    fun bindMovieSearchUseCase(movieSearchInteractor: MovieSearchInteractor): MovieSearchUseCase
}