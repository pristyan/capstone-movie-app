package com.aoc.capstone.presentation.movielist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.FragmentMovieListBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.model.view.Movie
import com.aoc.capstone.presentation.moviedetail.MovieDetailActivity
import com.aoc.capstone.presentation.movielist.adapter.MovieListAdapter
import com.aoc.capstone.presentation.movielist.di.DaggerMovieListComponent
import com.aoc.capstone.presentation.movielist.viewmodel.MovieListViewModel
import com.aoc.capstone.presentation.movielist.viewmodel.MovieListViewModelContract
import com.aoc.capstone.presentation.moviesearch.MovieSearchActivity
import com.aoc.core.base.fragment.BaseFragment
import com.aoc.core.util.onLoadMore
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class MovieListFragment :
    BaseFragment<MovieListViewModel, MovieListViewModelContract, FragmentMovieListBinding>(),
    MovieListView {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listAdapter: MovieListAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_movie_list
    }

    override fun getViewModelClass(): Class<MovieListViewModel> {
        return MovieListViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.observe(this, {
            binding.srlMovie.isRefreshing = it
        })

        viewModel.popularListError.observe(this, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.popularListSuccess.observe(this, {
            listAdapter.addItems(it)
        })
    }

    override fun setupDependencyInjection() {
        DaggerMovieListComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun initView() {
        binding.toolbar.inflateMenu(R.menu.menu_list_fragment)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_search -> {
                    MovieSearchActivity.startActivity(requireContext())
                }
                R.id.menu_favorite -> {
                    val clsName = Class.forName("com.aoc.favorite.presentation.favoritelist.FavoriteListActivity")
                    val intent = Intent(context, clsName)
                    startActivity(intent)
                }
            }
            true
        }

        binding.srlMovie.setOnRefreshListener {
            listAdapter.clearItems()
            viewModel.getPopularMovieList(true)
        }

        listAdapter.callback = object : MovieListAdapter.Callback {
            override fun onItemClick(movie: Movie) {
                MovieDetailActivity.startActivity(requireContext(), movie.id)
            }
        }

        binding.rvMovie.adapter = listAdapter
        binding.nvMovie.onLoadMore { viewModel.getPopularMovieList(false) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getPopularMovieList(true)
    }

}