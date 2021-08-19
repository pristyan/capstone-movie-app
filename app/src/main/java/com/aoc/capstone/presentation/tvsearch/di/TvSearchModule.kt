package com.aoc.capstone.presentation.tvsearch.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.domain.tvsearch.TvSearchInteractor
import com.aoc.capstone.domain.tvsearch.TvSearchUseCase
import com.aoc.capstone.presentation.tvsearch.viewmodel.TvSearchViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module(includes = [BaseFeatureModule::class])
interface TvSearchModule {

    @Binds
    fun bindViewModel(tvSearchViewModel: TvSearchViewModel): ViewModel

    @Binds
    fun bindTvSearchUseCase(tvSearchInteractor: TvSearchInteractor): TvSearchUseCase
}