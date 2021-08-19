package com.aoc.favorite.presentation.favoritelist.di

import androidx.lifecycle.ViewModel
import com.aoc.core.di.module.BaseFeatureModule
import com.aoc.favorite.domain.FavoriteListInteractor
import com.aoc.favorite.domain.FavoriteListUseCase
import com.aoc.favorite.presentation.favoritelist.viewmodel.FavoriteListViewModel
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module(includes = [BaseFeatureModule::class])
interface FavoriteListModule {

    @Binds
    fun bindViewModel(favoriteListViewModel: FavoriteListViewModel): ViewModel

    @Binds
    fun bindFavoriteListUseCase(
        favoriteListInteractor: FavoriteListInteractor
    ): FavoriteListUseCase
}