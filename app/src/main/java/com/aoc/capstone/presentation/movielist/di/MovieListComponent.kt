package com.aoc.capstone.presentation.movielist.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.movielist.MovieListFragment
import com.aoc.core.base.fragment.BaseFragmentComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/
@FeatureScope
@Component(modules = [MovieListModule::class], dependencies = [AppComponent::class])
interface MovieListComponent: BaseFragmentComponent<MovieListFragment>