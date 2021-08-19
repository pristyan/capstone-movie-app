package com.aoc.capstone

import android.app.Application
import com.aoc.core.di.component.CoreComponent
import com.aoc.core.di.component.CoreComponentProvider
import com.aoc.core.di.component.DaggerCoreComponent
import com.aoc.core.di.module.ApplicationModule


/**
 * Created by Chandra.
 **/

class MyApplication : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    private fun initDagger() {
        coreComponent = DaggerCoreComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun provideCoreComponent(): CoreComponent {
        return coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }
}