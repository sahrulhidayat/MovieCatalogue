package com.sahrul.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.vo.Resource

interface ItemDataSource {

    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getMovieDetails(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowDetails(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}