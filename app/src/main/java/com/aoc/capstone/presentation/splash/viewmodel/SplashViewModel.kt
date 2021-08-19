package com.aoc.capstone.presentation.splash.viewmodel

import com.aoc.core.base.viewmodel.BaseViewModel
import com.aoc.core.scheduler.SchedulerProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class SplashViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel(), SplashViewModelContract {

    override fun awaitLoading(): Job = launch(schedulerProvider.ui()) {
        setLoading(true)
        delay(1500)
        setLoading(false)
    }
}