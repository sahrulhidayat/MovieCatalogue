package com.sahrul.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sahrul.moviecatalogue.data.Repository
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.vo.Resource

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getMovieDetails(id: Int): LiveData<Resource<MovieEntity>> = repository.getMovieDetails(id)

    fun getTvShowDetails(id: Int): LiveData<Resource<TvShowEntity>> =
        repository.getTvShowDetails(id)

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavorite
        repository.setFavoriteMovie(movieEntity, newState)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFavorite
        repository.setFavoriteTvShow(tvShowEntity, newState)
    }
}