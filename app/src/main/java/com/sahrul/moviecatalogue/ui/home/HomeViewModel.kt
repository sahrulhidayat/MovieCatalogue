package com.sahrul.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sahrul.moviecatalogue.data.Repository
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.vo.Resource

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        repository.getMovies(sort)

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> =
        repository.getTvShows(sort)

}