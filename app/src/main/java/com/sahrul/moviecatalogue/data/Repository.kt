package com.sahrul.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sahrul.moviecatalogue.data.source.local.LocalDataSource
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.data.source.remote.ApiResponse
import com.sahrul.moviecatalogue.data.source.remote.RemoteDataSource
import com.sahrul.moviecatalogue.data.source.remote.response.MovieDetailsResponse
import com.sahrul.moviecatalogue.data.source.remote.response.ResultsMovie
import com.sahrul.moviecatalogue.data.source.remote.response.ResultsTvShow
import com.sahrul.moviecatalogue.data.source.remote.response.TvShowDetailsResponse
import com.sahrul.moviecatalogue.utils.AppExecutors
import com.sahrul.moviecatalogue.utils.ResponseFormatter.durationFormatter
import com.sahrul.moviecatalogue.utils.ResponseFormatter.movieCategoryFormatter
import com.sahrul.moviecatalogue.utils.ResponseFormatter.tvShowCategoryFormatter
import com.sahrul.moviecatalogue.vo.Resource

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ItemDataSource {

    override fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<ResultsMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<ResultsMovie>) {
                val movieList = ArrayList<MovieEntity>()
                for (movie in data) {
                    with(movie) {
                        movieList.add(
                            MovieEntity(
                                id,
                                posterPath,
                                title,
                                overview,
                                releaseDate,
                                voteAverage,
                            )
                        )
                    }
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetails(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object :
            NetworkBoundResource<MovieEntity, MovieDetailsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetails(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.category == null

            override fun createCall(): LiveData<ApiResponse<MovieDetailsResponse>> =
                remoteDataSource.getMovieDetails(movieId)

            override fun saveCallResult(data: MovieDetailsResponse) {
                lateinit var movie: MovieEntity
                with(data) {
                    if (movieId == data.id) {
                        movie = MovieEntity(
                            id,
                            posterPath,
                            title,
                            overview,
                            releaseDate,
                            voteAverage,
                            movieCategoryFormatter(genres),
                            durationFormatter(runtime),
                        )
                    }
                }
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<ResultsTvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvShow>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<ResultsTvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (tvShow in data) {
                    with(tvShow) {
                        tvShowList.add(
                            TvShowEntity(
                                id,
                                posterPath,
                                title,
                                overview,
                                releaseDate,
                                voteAverage,
                            )
                        )
                    }
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShowDetails(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object :
            NetworkBoundResource<TvShowEntity, TvShowDetailsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowDetails(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data?.category == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailsResponse>> =
                remoteDataSource.getTvShowDetails(tvShowId)

            override fun saveCallResult(data: TvShowDetailsResponse) {
                lateinit var tvShow: TvShowEntity
                with(data) {
                    if (tvShowId == data.id) {
                        tvShow = TvShowEntity(
                            id,
                            posterPath,
                            title,
                            overview,
                            releaseDate,
                            voteAverage,
                            tvShowCategoryFormatter(genres),
                        )
                    }
                }
                localDataSource.updateTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }
}