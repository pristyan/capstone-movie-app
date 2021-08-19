package com.aoc.favorite.presentation.favoritelist

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.model.view.Movie
import com.aoc.capstone.model.view.Tv
import com.aoc.capstone.presentation.moviedetail.MovieDetailActivity
import com.aoc.capstone.presentation.movielist.adapter.MovieListAdapter
import com.aoc.capstone.presentation.tvdetail.TvDetailActivity
import com.aoc.capstone.presentation.tvlist.adapter.TvListAdapter
import com.aoc.core.base.activity.BaseActivity
import com.aoc.favorite.R
import com.aoc.favorite.databinding.ActivityFavoriteListBinding
import com.aoc.favorite.presentation.favoritelist.di.DaggerFavoriteListComponent
import com.aoc.favorite.presentation.favoritelist.viewmodel.FavoriteListViewModel
import com.aoc.favorite.presentation.favoritelist.viewmodel.FavoriteListViewModelContract
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class FavoriteListActivity :
    BaseActivity<FavoriteListViewModel, FavoriteListViewModelContract, ActivityFavoriteListBinding>(),
    FavoriteListView {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var movieAdapter: MovieListAdapter

    @Inject
    lateinit var tvAdapter: TvListAdapter

    private var selectedIndex = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_favorite_list
    }

    override fun getViewModelClass(): Class<FavoriteListViewModel> {
        return FavoriteListViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() {
        viewModel.movieList.observe(this, {
            movieAdapter.setItems(it)
            binding.listSize = it.size
        })

        viewModel.tvList.observe(this, {
            tvAdapter.setItems(it)
            binding.listSize = it.size
        })
    }

    override fun setupDependencyInjection() {
        DaggerFavoriteListComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun initView() {
        binding.selectedMode = selectedIndex

        binding.toolbar.setNavigationOnClickListener { finish() }

        movieAdapter.callback = object : MovieListAdapter.Callback {
            override fun onItemClick(movie: Movie) {
                MovieDetailActivity.startActivity(this@FavoriteListActivity, movie.id)
            }
        }

        tvAdapter.callback = object : TvListAdapter.Callback {
            override fun onItemClick(item: Tv) {
                TvDetailActivity.startActivity(this@FavoriteListActivity, item.id)
            }
        }

        binding.tvMovie.setOnClickListener {
            if (selectedIndex != 0) {
                selectedIndex = 0
                binding.selectedMode = selectedIndex
                getFavoriteMovies()
            }
        }

        binding.tvSeries.setOnClickListener {
            if (selectedIndex != 1) {
                selectedIndex = 1
                binding.selectedMode = selectedIndex
                getFavoriteTvSeries()
            }
        }
    }

    override fun getFavoriteMovies() {
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = movieAdapter
        viewModel.getMovies()
    }

    override fun getFavoriteTvSeries() {
        binding.rvFavorite.layoutManager = GridLayoutManager(this, 2)
        binding.rvFavorite.adapter = tvAdapter
        viewModel.getTvSeries()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        if (selectedIndex == 0) getFavoriteMovies()
        else getFavoriteTvSeries()
    }
}