package com.aoc.capstone.presentation.tvlist.adapter

import com.aoc.capstone.R
import com.aoc.capstone.databinding.ListItemTvBinding
import com.aoc.capstone.model.view.Tv
import com.aoc.core.base.adapter.BaseRecyclerAdapter
import com.aoc.core.util.loadImage
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class TvListAdapter @Inject constructor(): BaseRecyclerAdapter<Tv, ListItemTvBinding>() {

    var callback: Callback? = null

    override fun getLayoutId(): Int {
        return R.layout.list_item_tv
    }

    override fun onBind(binding: ListItemTvBinding, position: Int, item: Tv) {
        binding.imgPoster.loadImage(item.fullPosterUrl)
        binding.root.setOnClickListener { callback?.onItemClick(item) }
        binding.tv = item
        binding.executePendingBindings()
    }

    interface Callback {
        fun onItemClick(item: Tv)
    }
}