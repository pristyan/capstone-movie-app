package com.aoc.capstone.presentation.tvsearch

import com.aoc.capstone.presentation.tvlist.adapter.TvListAdapter


/**
 * Created by Chandra.
 **/

interface TvSearchView {

    var tvListAdapter: TvListAdapter

    fun initView()

    fun observeKeyword()

    fun getKeyword(): String

    fun getResult(keyword: String, isRefresh: Boolean)

}