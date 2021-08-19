package com.aoc.favorite.presentation.favoritelist.di

import com.aoc.capstone.di.AppComponent
import com.aoc.core.base.activity.BaseActivityComponent
import com.aoc.core.di.scope.FeatureScope
import com.aoc.favorite.presentation.favoritelist.FavoriteListActivity
import dagger.Component


/**
 * Created by Chandra.
 **/

@FeatureScope
@Component(modules = [FavoriteListModule::class], dependencies = [AppComponent::class])
interface FavoriteListComponent: BaseActivityComponent<FavoriteListActivity>