package com.aoc.capstone.presentation.main.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.presentation.main.viewmodel.MainViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/
@Module(includes = [BaseFeatureModule::class])
interface MainModule {

    @Binds
    fun bindViewModel(mainViewModel: MainViewModel): ViewModel

}