package com.sahrul.moviecatalogue.di

import androidx.room.Room
import com.sahrul.moviecatalogue.data.source.local.room.ItemDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<ItemDatabase>().movieDao() }
    factory { get<ItemDatabase>().tvShowDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            ItemDatabase::class.java,
            "item_database"
        ).fallbackToDestructiveMigration().build()
    }
}