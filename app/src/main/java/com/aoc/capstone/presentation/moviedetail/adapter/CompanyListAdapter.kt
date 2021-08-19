package com.aoc.capstone.presentation.moviedetail.adapter

import com.aoc.capstone.R
import com.aoc.capstone.databinding.ListItemCompanyBinding
import com.aoc.capstone.model.view.ProductionCompany
import com.aoc.core.base.adapter.BaseRecyclerAdapter
import com.aoc.core.util.loadImage
import javax.inject.Inject


/**
 * Created by Chandra.
 **/

class CompanyListAdapter @Inject constructor() :
    BaseRecyclerAdapter<ProductionCompany, ListItemCompanyBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.list_item_company
    }

    override fun onBind(binding: ListItemCompanyBinding, position: Int, item: ProductionCompany) {
        binding.imgIcon.loadImage(item.fullLogoUrl)
        binding.company = item
        binding.executePendingBindings()
    }
}