package com.aoc.capstone.presentation.main

import androidx.fragment.app.Fragment


/**
 * Created by Chandra.
 **/

interface MainView {

    val pages: Map<Int, Pair<Fragment, String>>

    var selectedPage: Pair<Fragment, String>?

    fun initView()

    fun setActivePage(fragment: Fragment, tag: String)
}