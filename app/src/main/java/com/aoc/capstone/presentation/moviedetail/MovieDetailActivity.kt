package com.aoc.capstone.presentation.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.ActivityMovieDetailBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.presentation.moviedetail.adapter.CompanyListAdapter
import com.aoc.capstone.presentation.moviedetail.di.DaggerMovieDetailComponent
import com.aoc.capstone.presentation.moviedetail.viewmodel.MovieDetailViewModel
import com.aoc.capstone.presentation.moviedetail.viewmodel.MovieDetailViewModelContract
import com.aoc.core.base.activity.BaseActivity
import com.aoc.core.util.loadImage
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class MovieDetailActivity :
    BaseActivity<MovieDetailViewModel, MovieDetailViewModelContract, ActivityMovieDetailBinding>(),
    MovieDetailView {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var companyListAdapter: CompanyListAdapter

    companion object {
        private const val ID = "id"

        fun startActivity(context: Context, id: Int) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(ID, id)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_movie_detail
    }

    override fun getViewModelClass(): Class<MovieDetailViewModel> {
        return MovieDetailViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.observe(this, {
            binding.srlDetail.isRefreshing = it
        })

        viewModel.movieDetailError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.movieDetailSuccess.observe(this, {
            binding.imgPoster.loadImage(it.fullPosterUrl)
            binding.movie = it
            companyListAdapter.setItems(it.productionCompanies)
            it.genres.forEach { genre ->
                val chip = Chip(this)
                chip.text = genre.name
                chip.textSize = 12f
                chip.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                binding.cgGenre.addView(chip)
            }
        })

        viewModel.isFavoriteMovie.observe(this, {
            binding.fabFavorite.visibility = View.VISIBLE
            binding.fabFavorite.setImageResource(
                if (it) R.drawable.ic_favorite_red_24dp
                else R.drawable.ic_favorite_grey_24dp
            )
        })
    }

    override fun setupDependencyInjection() {
        DaggerMovieDetailComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.srlDetail.setOnRefreshListener {
            binding.cgGenre.removeAllViews()
            getDetail()
        }

        binding.fabFavorite.setOnClickListener {
            viewModel.isFavoriteMovie.value?.let {
                if (it) viewModel.deleteFavorite()
                else viewModel.setFavorite()
            }
        }

        binding.rvCompany.adapter = companyListAdapter
    }

    override fun getMovieId(): Int {
        return intent.getIntExtra(ID, 0)
    }

    override fun getDetail() {
        viewModel.getMovieDetail(getMovieId())
        viewModel.isFavorite(getMovieId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        getDetail()
    }
}