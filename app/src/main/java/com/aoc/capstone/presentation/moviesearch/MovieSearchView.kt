package com.aoc.capstone.presentation.moviesearch

import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.presentation.movielist.adapter.MovieListAdapter


/**
 * Created by Chandra.
 **/

interface MovieSearchView {

    var viewModelProviderFactory: ViewModelProvider.Factory

    var movieListAdapter: MovieListAdapter

    fun initView()

    fun observeKeyword()

    fun getKeyword(): String

    fun getResult(keyword: String, isRefresh: Boolean)

}