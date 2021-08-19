package com.aoc.capstone.presentation.main.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.main.MainActivity
import com.aoc.core.base.activity.BaseActivityComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/
@FeatureScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent: BaseActivityComponent<MainActivity>