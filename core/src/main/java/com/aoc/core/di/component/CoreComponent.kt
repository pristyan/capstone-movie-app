package com.aoc.core.di.component

import android.content.Context
import com.aoc.core.di.module.ApplicationModule
import com.aoc.core.di.module.CoreModule
import com.aoc.core.scheduler.SchedulerProvider
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Created by Chandra.
 **/

@Singleton
@Component(modules = [ApplicationModule::class, CoreModule::class])
interface CoreComponent {

    fun gson(): Gson

    fun context(): Context

    fun retrofit(): Retrofit

    fun schedulerProvider(): SchedulerProvider

}