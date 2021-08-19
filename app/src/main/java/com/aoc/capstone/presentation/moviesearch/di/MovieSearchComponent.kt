package com.aoc.capstone.presentation.moviesearch.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.moviesearch.MovieSearchActivity
import com.aoc.core.base.activity.BaseActivityComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/

@FeatureScope
@Component(modules = [MovieSearchModule::class], dependencies = [AppComponent::class])
interface MovieSearchComponent: BaseActivityComponent<MovieSearchActivity>