package com.aoc.capstone.di

import com.aoc.capstone.data.service.ApiService
import com.aoc.core.di.scope.ModuleScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


/**
 * Created by Chandra.
 **/
@Module
object ApiServiceModule {

    @ModuleScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}