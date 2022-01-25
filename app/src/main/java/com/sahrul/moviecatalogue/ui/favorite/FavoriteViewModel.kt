package com.sahrul.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sahrul.moviecatalogue.data.Repository
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val repository: Repository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = repository.getFavoriteMovies()

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = repository.getFavoriteTvShows()
}