package com.aoc.capstone.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aoc.capstone.model.db.TvDbEntity


/**
 * Created by Chandra.
 **/

@Dao
interface TvDao {

    @Query("select * from tv")
    fun getAll(): List<TvDbEntity>

    @Query("select * from tv where id == :id")
    fun getById(id: Int): List<TvDbEntity>

    @Insert
    fun insert(TvDbEntity: TvDbEntity)

    @Delete
    fun delete(TvDbEntity: TvDbEntity)
}