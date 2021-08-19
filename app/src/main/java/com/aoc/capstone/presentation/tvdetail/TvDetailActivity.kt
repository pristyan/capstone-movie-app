package com.aoc.capstone.presentation.tvdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.ActivityTvDetailBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.presentation.moviedetail.adapter.CompanyListAdapter
import com.aoc.capstone.presentation.tvdetail.di.DaggerTvDetailComponent
import com.aoc.capstone.presentation.tvdetail.viewmodel.TvDetailViewModel
import com.aoc.capstone.presentation.tvdetail.viewmodel.TvDetailViewModelContract
import com.aoc.core.base.activity.BaseActivity
import com.aoc.core.util.loadImage
import com.google.android.material.chip.Chip
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class TvDetailActivity :
    BaseActivity<TvDetailViewModel, TvDetailViewModelContract, ActivityTvDetailBinding>(),
    TvDetailView {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var companyListAdapter: CompanyListAdapter

    companion object {

        private const val ID = "id"

        fun startActivity(context: Context, id: Int) {
            val intent = Intent(context, TvDetailActivity::class.java)
            intent.putExtra(ID, id)
            context.startActivity(intent)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_tv_detail
    }

    override fun getViewModelClass(): Class<TvDetailViewModel> {
        return TvDetailViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.observe(this, {
            binding.srlDetail.isRefreshing = it
        })

        viewModel.detailError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.detailSuccess.observe(this, {
            binding.containerLastEpisode.episode = it.lastEpisodeToAir
            binding.containerLastEpisode.imgEpisode.loadImage(it.lastEpisodeToAir.fullPosterUrl)

            binding.containerNextEpisode.episode = it.nextEpisodeToAir
            binding.containerNextEpisode.imgEpisode.loadImage(it.lastEpisodeToAir.fullPosterUrl)

            binding.imgPoster.loadImage(it.fullPosterUrl)
            binding.tv = it
            companyListAdapter.setItems(it.productionCompanies)
            it.genres.forEach { genre ->
                val chip = Chip(this)
                chip.text = genre.name
                chip.textSize = 12f
                chip.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                binding.cgGenre.addView(chip)
            }
        })

        viewModel.isFavorite.observe(this, {
            binding.fabFavorite.visibility = View.VISIBLE
            binding.fabFavorite.setImageResource(
                if (it) R.drawable.ic_favorite_red_24dp
                else R.drawable.ic_favorite_grey_24dp
            )
        })
    }

    override fun setupDependencyInjection() {
        DaggerTvDetailComponent.builder()
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
        binding.rvCompany.adapter = companyListAdapter
        binding.fabFavorite.setOnClickListener {
            viewModel.isFavorite.value?.let {
                if (it) viewModel.deleteFavorite()
                else viewModel.setFavorite()
            }
        }
    }

    override fun getTvId(): Int {
        return intent.getIntExtra(ID, 0)
    }

    override fun getDetail() {
        viewModel.getDetail(getTvId())
        viewModel.isFavorite(getTvId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        getDetail()
    }
}