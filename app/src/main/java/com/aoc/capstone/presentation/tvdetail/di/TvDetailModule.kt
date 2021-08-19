package com.aoc.capstone.presentation.tvdetail.di

import androidx.lifecycle.ViewModel
import com.aoc.capstone.domain.tvdetail.TvDetailInteractor
import com.aoc.capstone.domain.tvdetail.TvDetailUseCase
import com.aoc.capstone.presentation.tvdetail.viewmodel.TvDetailViewModel
import com.aoc.core.di.module.BaseFeatureModule
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module(includes = [BaseFeatureModule::class])
interface TvDetailModule {

    @Binds
    fun bindViewModel(tvDetailViewModel: TvDetailViewModel): ViewModel

    @Binds
    fun bindTvDetailUseCase(tvDetailInteractor: TvDetailInteractor): TvDetailUseCase
}