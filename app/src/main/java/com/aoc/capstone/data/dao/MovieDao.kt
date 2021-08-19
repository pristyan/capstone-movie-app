package com.aoc.capstone.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aoc.capstone.model.db.MovieDbEntity


/**
 * Created by Chandra.
 **/

@Dao
interface MovieDao {

    @Query("select * from movie")
    fun getAll(): List<MovieDbEntity>

    @Query("select * from movie where id == :id")
    fun getById(id: Int): List<MovieDbEntity>

    @Insert
    fun insert(MovieDbEntity: MovieDbEntity)

    @Delete
    fun delete(MovieDbEntity: MovieDbEntity)
}