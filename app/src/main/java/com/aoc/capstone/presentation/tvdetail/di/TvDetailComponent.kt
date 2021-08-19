package com.aoc.capstone.presentation.tvdetail.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.tvdetail.TvDetailActivity
import com.aoc.core.base.activity.BaseActivityComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/

@FeatureScope
@Component(modules = [TvDetailModule::class], dependencies = [AppComponent::class])
interface TvDetailComponent: BaseActivityComponent<TvDetailActivity>