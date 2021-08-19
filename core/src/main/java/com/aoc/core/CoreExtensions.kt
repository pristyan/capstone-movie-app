package com.aoc.core

import android.app.Activity
import androidx.fragment.app.Fragment
import com.aoc.core.di.component.CoreComponentProvider


/**
 * Created by Chandra.
 **/

fun Activity.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented")

fun Fragment.coreComponent() = requireActivity().coreComponent()