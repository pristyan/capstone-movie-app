package com.aoc.capstone.di

import android.content.Context
import com.aoc.capstone.data.repository.AppRepository
import com.aoc.capstone.db.AppDatabase
import com.aoc.core.di.component.CoreComponent
import com.aoc.core.di.scope.ModuleScope
import com.aoc.core.scheduler.SchedulerProvider
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit


/**
 * Created by Chandra.
 **/

@ModuleScope
@Component(modules = [AppModule::class], dependencies = [CoreComponent::class])
interface AppComponent {

    fun gson(): Gson

    fun context(): Context

    fun retrofit(): Retrofit

    fun schedulerProvider(): SchedulerProvider

    fun appRepository(): AppRepository

    fun appDatabase(): AppDatabase
}