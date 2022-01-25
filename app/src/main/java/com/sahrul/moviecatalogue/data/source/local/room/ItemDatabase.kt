package com.sahrul.moviecatalogue.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao

}