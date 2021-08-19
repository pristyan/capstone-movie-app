package com.aoc.capstone.presentation.tvlist.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.domain.tvlist.TvListInteractor
import com.aoc.capstone.domain.tvlist.TvListUseCase
import com.aoc.capstone.presentation.tvlist.viewmodel.TvListViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module(includes = [BaseFeatureModule::class])
interface TvListModule {

    @Binds
    fun bindViewModel(tvListViewModel: TvListViewModel): ViewModel

    @Binds
    fun bindTvListUseCase(tvListInteractor: TvListInteractor): TvListUseCase
}