package com.aoc.capstone.di

import android.content.Context
import androidx.room.Room
import com.aoc.capstone.db.AppDatabase
import com.aoc.capstone.db.DBConstant
import com.aoc.core.di.scope.ModuleScope
import dagger.Module
import dagger.Provides


/**
 * Created by Chandra.
 **/
@Module
object DBModule {

    @ModuleScope
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DBConstant.NAME
        ).build()
    }
}