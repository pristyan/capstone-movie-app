package com.aoc.capstone.presentation.splash.di

import com.aoc.capstone.di.AppComponent
import com.aoc.capstone.presentation.splash.SplashActivity
import com.aoc.core.base.activity.BaseActivityComponent
import com.aoc.core.di.scope.FeatureScope
import dagger.Component


/**
 * Created by Chandra.
 **/

@FeatureScope
@Component(modules = [SplashModule::class], dependencies = [AppComponent::class])
interface SplashComponent: BaseActivityComponent<SplashActivity>