package com.aoc.capstone.presentation.tvsearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.ActivityTvSearchBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.model.view.Tv
import com.aoc.capstone.presentation.tvdetail.TvDetailActivity
import com.aoc.capstone.presentation.tvsearch.di.DaggerTvSearchComponent
import com.aoc.capstone.presentation.tvlist.adapter.TvListAdapter
import com.aoc.capstone.presentation.tvsearch.viewmodel.TvSearchViewModel
import com.aoc.capstone.presentation.tvsearch.viewmodel.TvSearchViewModelContract
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

class TvSearchActivity :
    BaseActivity<TvSearchViewModel, TvSearchViewModelContract, ActivityTvSearchBinding>(),
    TvSearchView, CoroutineScope {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    override lateinit var tvListAdapter: TvListAdapter

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, TvSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_tv_search
    }

    override fun getViewModelClass(): Class<TvSearchViewModel> {
        return TvSearchViewModel::class.java
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
            tvListAdapter.addItems(it)
        })
    }

    override fun setupDependencyInjection() {
        DaggerTvSearchComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.rvSearch.adapter = tvListAdapter
        binding.srlSearch.setOnRefreshListener { getResult(getKeyword(), true) }
        binding.nvSearch.onLoadMore { getResult(getKeyword(), false) }

        tvListAdapter.callback = object : TvListAdapter.Callback {
            override fun onItemClick(item: Tv) {
                TvDetailActivity.startActivity(this@TvSearchActivity, item.id)
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
        if (isRefresh) tvListAdapter.clearItems()
        viewModel.searchTv(keyword, isRefresh)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeKeyword()
    }
}