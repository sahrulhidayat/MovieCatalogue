package com.sahrul.moviecatalogue.di

import com.sahrul.moviecatalogue.data.source.local.LocalDataSource
import com.sahrul.moviecatalogue.data.source.remote.RemoteDataSource
import com.sahrul.moviecatalogue.network.ApiConfig
import com.sahrul.moviecatalogue.utils.AppExecutors
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.getApiService() }
    single { RemoteDataSource() }
    single { LocalDataSource(get(), get()) }
    single { AppExecutors() }
}