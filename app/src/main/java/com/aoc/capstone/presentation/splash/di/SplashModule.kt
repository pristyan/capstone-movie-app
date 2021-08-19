package com.aoc.capstone.presentation.splash.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.presentation.splash.viewmodel.SplashViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/
@Module(includes = [BaseFeatureModule::class])
interface SplashModule {

    @Binds
    fun bindViewModel(splashViewModel: SplashViewModel): ViewModel
}