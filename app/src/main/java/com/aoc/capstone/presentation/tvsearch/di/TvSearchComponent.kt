package com.aoc.capstone.presentation.tvsearch.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.tvsearch.TvSearchActivity
import com.aoc.core.base.activity.BaseActivityComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/

@FeatureScope
@Component(modules = [TvSearchModule::class], dependencies = [AppComponent::class])
interface TvSearchComponent: BaseActivityComponent<TvSearchActivity>