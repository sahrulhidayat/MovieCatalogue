package com.sahrul.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.data.source.local.room.MovieDao
import com.sahrul.moviecatalogue.data.source.local.room.TvShowDao
import com.sahrul.moviecatalogue.utils.SortUtils

class LocalDataSource(private val mMovieDao: MovieDao, private val mTvShowDao: TvShowDao) {
    fun getMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return mMovieDao.getMovies(query)
    }

    fun getTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> {
        val query = SortUtils.getSortedQueryTvShows(sort)
        return mTvShowDao.getTvShows(query)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> {
        return mMovieDao.getFavoriteMovies()
    }

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> {
        return mTvShowDao.getFavoriteTvShows()
    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        mMovieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        mTvShowDao.updateTvShow(tvShow)
    }

    fun getMovieDetails(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovieDetails(movieId)

    fun getTvShowDetails(tvShowId: Int): LiveData<TvShowEntity> =
        mTvShowDao.getTvShowDetails(tvShowId)

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mTvShowDao.insertTvShows(tvShows)

    fun updateMovie(movie: MovieEntity) = mMovieDao.updateMovie(movie)

    fun updateTvShow(tvShow: TvShowEntity) = mTvShowDao.updateTvShow(tvShow)
}