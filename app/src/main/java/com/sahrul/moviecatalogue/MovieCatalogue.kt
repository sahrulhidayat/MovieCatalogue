package com.sahrul.moviecatalogue

import android.app.Application
import com.sahrul.moviecatalogue.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieCatalogue : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieCatalogue)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule,
                )
            )
        }
    }
}