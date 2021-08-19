package com.aoc.capstone.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.ActivitySplashBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.presentation.main.MainActivity
import com.aoc.capstone.presentation.splash.di.DaggerSplashComponent
import com.aoc.capstone.presentation.splash.viewmodel.SplashViewModel
import com.aoc.capstone.presentation.splash.viewmodel.SplashViewModelContract
import com.aoc.core.base.activity.BaseActivity
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class SplashActivity :
    BaseActivity<SplashViewModel, SplashViewModelContract, ActivitySplashBinding>(), SplashView {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.observe(this, {
            if (!it) navigateToMain()
        })
    }

    override fun setupDependencyInjection() {
        DaggerSplashComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.awaitLoading()
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}