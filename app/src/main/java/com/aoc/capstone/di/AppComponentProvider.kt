package com.aoc.capstone.di

import com.aoc.core.di.component.CoreComponent


/**
 * Created by Chandra.
 **/
object AppComponentProvider {

    private var appComponent: AppComponent? = null

    fun provideAppComponent(coreComponent: CoreComponent): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .coreComponent(coreComponent)
                .build()
        }
        return appComponent as AppComponent
    }

}