package com.aoc.capstone.presentation.moviesearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.ActivityMovieSearchBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.model.view.Movie
import com.aoc.capstone.presentation.moviedetail.MovieDetailActivity
import com.aoc.capstone.presentation.movielist.adapter.MovieListAdapter
import com.aoc.capstone.presentation.moviesearch.di.DaggerMovieSearchComponent
import com.aoc.capstone.presentation.moviesearch.viewmodel.MovieSearchViewModel
import com.aoc.capstone.presentation.moviesearch.viewmodel.MovieSearchViewModelContract
import com.aoc.core.base.activity.BaseActivity
import com.aoc.core.util.onLoadMore
import com.aoc.core.util.onTextChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by Chandra.
 **/

class MovieSearchActivity :
    BaseActivity<MovieSearchViewModel, MovieSearchViewModelContract, ActivityMovieSearchBinding>(),
    MovieSearchView, CoroutineScope {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    override lateinit var movieListAdapter: MovieListAdapter

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MovieSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_movie_search
    }

    override fun getViewModelClass(): Class<MovieSearchViewModel> {
        return MovieSearchViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.observe(this, {
            binding.srlSearch.isRefreshing = it
        })

        viewModel.listError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.listSuccess.observe(this, {
            binding.listSize = it.size
            movieListAdapter.addItems(it)
        })
    }

    override fun setupDependencyInjection() {
        DaggerMovieSearchComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.rvSearch.adapter = movieListAdapter
        binding.srlSearch.setOnRefreshListener { getResult(getKeyword(), true) }
        binding.nvSearch.onLoadMore { getResult(getKeyword(), false) }
        movieListAdapter.callback = object : MovieListAdapter.Callback {
            override fun onItemClick(movie: Movie) {
                MovieDetailActivity.startActivity(this@MovieSearchActivity, movie.id)
            }
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun observeKeyword() {
        binding.edtKeyword.onTextChange()
            .debounce(500)
            .onEach { getResult(it.toString(), true) }
            .launchIn(this)
    }

    override fun getKeyword(): String {
        return binding.edtKeyword.text.toString()
    }

    override fun getResult(keyword: String, isRefresh: Boolean) {
        if (isRefresh) movieListAdapter.clearItems()
        viewModel.searchMovie(keyword, isRefresh)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeKeyword()
    }
}