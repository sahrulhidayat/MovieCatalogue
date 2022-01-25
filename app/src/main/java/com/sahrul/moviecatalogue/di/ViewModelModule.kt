package com.sahrul.moviecatalogue.di

import com.sahrul.moviecatalogue.ui.detail.DetailViewModel
import com.sahrul.moviecatalogue.ui.favorite.FavoriteViewModel
import com.sahrul.moviecatalogue.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}