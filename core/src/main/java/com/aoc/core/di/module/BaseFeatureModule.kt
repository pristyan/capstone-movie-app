package com.aoc.core.di.module

import androidx.lifecycle.ViewModelProvider
import com.aoc.core.base.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module
interface BaseFeatureModule {

    @Binds
    fun bindViewModelProviderFactory(
        viewModelProviderFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory
}