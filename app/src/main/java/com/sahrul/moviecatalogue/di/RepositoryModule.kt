package com.sahrul.moviecatalogue.di

import com.sahrul.moviecatalogue.data.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get(), get(), get()) }
}