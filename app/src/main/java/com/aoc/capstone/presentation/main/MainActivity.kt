package com.aoc.capstone.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.ActivityMainBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.presentation.main.di.DaggerMainComponent
import com.aoc.capstone.presentation.main.viewmodel.MainViewModel
import com.aoc.capstone.presentation.main.viewmodel.MainViewModelContract
import com.aoc.capstone.presentation.movielist.MovieListFragment
import com.aoc.capstone.presentation.tvlist.TvListFragment
import com.aoc.core.base.activity.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel, MainViewModelContract, ActivityMainBinding>(),
    MainView {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() = Unit

    override fun setupDependencyInjection() {
        DaggerMainComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun initView() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movie_fragment -> setActivePage(MovieListFragment(), "Movie")
                R.id.tv_fragment -> setActivePage(TvListFragment(), "TV")
            }
            true
        }

        setActivePage(MovieListFragment(), "Movie")
    }

    override fun setActivePage(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, tag)
        transaction.commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

}