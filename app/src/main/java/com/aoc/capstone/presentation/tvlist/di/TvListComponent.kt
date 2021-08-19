package com.aoc.capstone.presentation.tvlist.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.tvlist.TvListFragment
import com.aoc.core.base.fragment.BaseFragmentComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/

@FeatureScope
@Component(modules = [TvListModule::class], dependencies = [AppComponent::class])
interface TvListComponent: BaseFragmentComponent<TvListFragment>