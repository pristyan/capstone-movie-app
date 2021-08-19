package com.aoc.capstone.presentation.splash.viewmodel

import com.aoc.core.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job


/**
 * Created by Chandra.
 **/

interface SplashViewModelContract: BaseViewModelContract {

    fun awaitLoading(): Job

}