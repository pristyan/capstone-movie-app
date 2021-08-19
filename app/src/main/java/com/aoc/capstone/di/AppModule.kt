package com.aoc.capstone.di

import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.data.repository.AppRepositoryImpl
import com.aoc.capstone.data.source.local.AppLocalDataSource
import com.aoc.capstone.data.source.local.AppLocalDataSourceImpl
import com.aoc.capstone.data.source.remote.AppRemoteDataSource
import com.aoc.capstone.data.source.remote.AppRemoteDataSourceImpl
import dagger.Binds
import dagger.Module


/**
 * Created by Chandra.
 **/

@Module(includes = [ApiServiceModule::class, DBModule::class])
interface AppModule {

    // Data Source
    @Binds
    fun bindAppRemoteDataSource(
        appRemoteDataSourceImpl: AppRemoteDataSourceImpl
    ): AppRemoteDataSource

    @Binds
    fun bindAppLocalDataSource(
        appLocalDataSourceImpl: AppLocalDataSourceImpl
    ): AppLocalDataSource

    // Repository
    @Binds
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

}