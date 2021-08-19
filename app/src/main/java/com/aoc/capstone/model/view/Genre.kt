package com.aoc.capstone.model.view

import com.aoc.capstone.model.entity.GenreEntity
import java.io.Serializable

data class Genre(
    val id: Int,
    val name: String
): Serializable {

    constructor(entity: GenreEntity?): this(
        id = entity?.id ?: 0,
        name = entity?.name.orEmpty()
    )
}