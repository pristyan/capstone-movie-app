package com.aoc.capstone.presentation.movielist.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.domain.movielist.MovieListInteractor
import com.aoc.capstone.domain.movielist.MovieListUseCase
import com.aoc.capstone.presentation.movielist.viewmodel.MovieListViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/
@Module(includes = [BaseFeatureModule::class])
interface MovieListModule {

    @Binds
    fun bindViewModel(movieListViewModel: MovieListViewModel): ViewModel

    @Binds
    fun bindMovieListUseCase(movieListInteractor: MovieListInteractor): MovieListUseCase
}