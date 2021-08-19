package com.aoc.capstone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aoc.capstone.data.dao.MovieDao
import com.aoc.capstone.data.dao.TvDao
import com.aoc.capstone.model.db.MovieDbEntity
import com.aoc.capstone.model.db.TvDbEntity


/**
 * Created by Chandra.
 **/

@Database(entities = [MovieDbEntity::class, TvDbEntity::class], version = DBConstant.VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvDao(): TvDao

}