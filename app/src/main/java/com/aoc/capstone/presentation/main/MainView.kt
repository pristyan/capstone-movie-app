package com.aoc.capstone.presentation.main

import androidx.fragment.app.Fragment


/**
 * Created by Chandra.
 **/

interface MainView {

    fun initView()

    fun setActivePage(fragment: Fragment, tag: String)
}