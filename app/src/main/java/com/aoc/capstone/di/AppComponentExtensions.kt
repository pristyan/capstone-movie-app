package com.aoc.capstone.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.aoc.core.coreComponent


/**
 * Created by Chandra.
 **/

fun Activity.appComponent(): AppComponent {
    return AppComponentProvider.provideAppComponent(coreComponent())
}

fun Fragment.appComponent(): AppComponent {
    return AppComponentProvider.provideAppComponent(coreComponent())
}