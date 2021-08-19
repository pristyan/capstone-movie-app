package com.aoc.capstone.presentation.movielist.adapter

import com.aoc.capstone.R
import com.aoc.capstone.databinding.ListItemMovieBinding
import com.aoc.capstone.model.view.Movie
import com.aoc.core.base.adapter.BaseRecyclerAdapter
import com.aoc.core.util.loadImage
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class MovieListAdapter @Inject constructor() : BaseRecyclerAdapter<Movie, ListItemMovieBinding>() {

    var callback: Callback? = null

    override fun getLayoutId(): Int {
        return R.layout.list_item_movie
    }

    override fun onBind(binding: ListItemMovieBinding, position: Int, item: Movie) {
        binding.imgPoster.loadImage(item.fullPosterUrl)
        binding.movie = item
        binding.root.setOnClickListener { callback?.onItemClick(item) }
        binding.executePendingBindings()
    }

    interface Callback {
        fun onItemClick(movie: Movie)
    }
}