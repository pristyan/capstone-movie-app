package com.aoc.capstone.model.view

import com.aoc.capstone.model.entity.ProductionCompanyEntity
import com.aoc.core.constant.NetworkConstant
import java.io.Serializable

data class ProductionCompany(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
): Serializable {

    constructor(entity: ProductionCompanyEntity?) : this(
        id = entity?.id ?: 0,
        logoPath = entity?.logoPath.orEmpty(),
        name = entity?.name.orEmpty(),
        originCountry = entity?.originCountry.orEmpty()
    )

    val fullLogoUrl: String
        get() = NetworkConstant.IMG_BASE_URL + logoPath
}