package com.aoc.capstone.presentation.tvlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aoc.capstone.R
import com.aoc.capstone.databinding.FragmentTvListBinding
import com.aoc.capstone.di.appComponent
import com.aoc.capstone.model.view.Tv
import com.aoc.capstone.presentation.tvdetail.TvDetailActivity
import com.aoc.capstone.presentation.tvlist.adapter.TvListAdapter
import com.aoc.capstone.presentation.tvlist.di.DaggerTvListComponent
import com.aoc.capstone.presentation.tvlist.viewmodel.TvListViewModel
import com.aoc.capstone.presentation.tvlist.viewmodel.TvListViewModelContract
import com.aoc.capstone.presentation.tvsearch.TvSearchActivity
import com.aoc.core.base.fragment.BaseFragment
import com.aoc.core.util.onLoadMore
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class TvListFragment :
    BaseFragment<TvListViewModel, TvListViewModelContract, FragmentTvListBinding>(),
    TvListView {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listAdapter: TvListAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_tv_list
    }

    override fun getViewModelClass(): Class<TvListViewModel> {
        return TvListViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return viewModelProviderFactory
    }

    override fun subscribeLiveData() {
        viewModel.isLoading.observe(this, {
            binding.srlTv.isRefreshing = it
        })

        viewModel.popularListError.observe(this, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.popularListSuccess.observe(this, {
            listAdapter.addItems(it)
        })
    }

    override fun setupDependencyInjection() {
        DaggerTvListComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this)
    }

    override fun initView() {
        binding.toolbar.inflateMenu(R.menu.menu_list_fragment)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_search -> {
                    TvSearchActivity.startActivity(requireContext())
                }
                R.id.menu_favorite -> {
                    val clsName = Class.forName("com.aoc.favorite.presentation.favoritelist.FavoriteListActivity")
                    val intent = Intent(context, clsName)
                    startActivity(intent)
                }
            }
            true
        }

        binding.srlTv.setOnRefreshListener {
            listAdapter.clearItems()
            viewModel.getPopularTvList(true)
        }

        listAdapter.callback = object : TvListAdapter.Callback {
            override fun onItemClick(item: Tv) {
                TvDetailActivity.startActivity(requireContext(), item.id)
            }
        }

        binding.rvTv.adapter = listAdapter
        binding.nvTv.onLoadMore { viewModel.getPopularTvList(false) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getPopularTvList(true)
    }
}