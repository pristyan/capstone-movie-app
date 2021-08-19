package com.aoc.core.di.module

import com.aoc.core.network.NetworkInterceptor
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor


/**
 * Created by Chandra.
 **/

@Module
interface InterceptorModule {

    @Binds
    fun bindInterceptor(networkInterceptor: NetworkInterceptor): Interceptor

}