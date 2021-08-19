package com.aoc.capstone.presentation.moviedetail.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.moviedetail.MovieDetailActivity
import com.aoc.core.base.activity.BaseActivityComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/

@FeatureScope
@Component(modules = [MovieDetailModule::class], dependencies = [AppComponent::class])
interface MovieDetailComponent: BaseActivityComponent<MovieDetailActivity>