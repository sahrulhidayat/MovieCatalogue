package com.sahrul.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.sahrul.moviecatalogue.data.source.local.LocalDataSource
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.data.source.remote.RemoteDataSource
import com.sahrul.moviecatalogue.utils.*
import com.sahrul.moviecatalogue.utils.ResponseFormatter.durationFormatter
import com.sahrul.moviecatalogue.utils.ResponseFormatter.movieCategoryFormatter
import com.sahrul.moviecatalogue.utils.ResponseFormatter.tvShowCategoryFormatter
import com.sahrul.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryTest {

    private val sort = SortUtils.NEWEST
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val repository = FakeRepository(remote, local, appExecutors)
    private val moviesResponse = DummyMovies.generateRemoteDummyMovies()
    private val movieDetailsResponse = DummyMovies.generateRemoteDummyMovieDetails()
    private val movieId = moviesResponse[0].id
    private val tvShowsResponse = DummyTvShows.generateRemoteDummyTvShows()
    private val tvShowDetailsResponse = DummyTvShows.generateRemoteDummyTvShowDetails()
    private val tvShowId = tvShowsResponse[0].id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getMovies() {
        val dummyMovies =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies(sort)).thenReturn(dummyMovies)
        repository.getMovies(sort)

        val movieEntities =
            Resource.success(PagedListTestUtils.mockPagedList(DummyMovies.generateDummyMovies()))
        verify(local).getMovies(sort)
        assertNotNull(movieEntities.data)
        assertEquals(moviesResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetails() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DummyMovies.generateDummyMovies()[0]

        `when`(local.getMovieDetails(movieId)).thenReturn(dummyMovie)
        val movieDetailsEntity = LiveDataTestUtil.getValue(repository.getMovieDetails(movieId))
        verify(local).getMovieDetails(eq(movieId))
        assertNotNull(movieDetailsEntity)
        assertEquals(movieDetailsResponse.id, movieDetailsEntity.data?.id)
        assertEquals(movieDetailsResponse.posterPath, movieDetailsEntity.data?.image)
        assertEquals(movieDetailsResponse.title, movieDetailsEntity.data?.title)
        assertEquals(movieDetailsResponse.overview, movieDetailsEntity.data?.overview)
        assertEquals(movieDetailsResponse.releaseDate, movieDetailsEntity.data?.release)
        assertEquals(
            movieDetailsResponse.voteAverage.toString(),
            movieDetailsEntity.data?.ratings.toString()
        )
        assertEquals(
            movieCategoryFormatter(movieDetailsResponse.genres),
            movieDetailsEntity.data?.category
        )
        assertEquals(
            durationFormatter(movieDetailsResponse.runtime),
            movieDetailsEntity.data?.duration
        )
    }

    @Test
    fun getTvShows() {
        val dummyTvShows =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows(sort)).thenReturn(dummyTvShows)
        repository.getTvShows(sort)

        val tvShowEntities =
            Resource.success(PagedListTestUtils.mockPagedList(DummyTvShows.generateDummyTvShows()))
        verify(local).getTvShows(sort)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowsResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetails() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = DummyTvShows.generateDummyTvShows()[0]

        `when`(local.getTvShowDetails(tvShowId)).thenReturn(dummyTvShow)
        val tvShowEntity = LiveDataTestUtil.getValue(repository.getTvShowDetails(tvShowId))
        verify(local).getTvShowDetails(eq(tvShowId))
        assertNotNull(tvShowEntity)
        assertEquals(tvShowDetailsResponse.id, tvShowEntity.data?.id)
        assertEquals(tvShowDetailsResponse.posterPath, tvShowEntity.data?.image)
        assertEquals(tvShowDetailsResponse.title, tvShowEntity.data?.title)
        assertEquals(tvShowDetailsResponse.overview, tvShowEntity.data?.overview)
        assertEquals(tvShowDetailsResponse.releaseDate, tvShowEntity.data?.release)
        assertEquals(
            tvShowDetailsResponse.voteAverage.toString(),
            tvShowEntity.data?.ratings.toString()
        )
        assertEquals(
            tvShowCategoryFormatter(tvShowDetailsResponse.genres),
            tvShowEntity.data?.category
        )
    }

    @Test
    fun getFavoriteMovies() {
        val favoriteMovies =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(favoriteMovies)
        repository.getFavoriteMovies()

        val favoriteMovieEntities =
            Resource.success(PagedListTestUtils.mockPagedList(DummyMovies.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(favoriteMovieEntities.data)
        assertEquals(moviesResponse.size.toLong(), favoriteMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val favoriteTvShows =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(favoriteTvShows)
        repository.getFavoriteTvShows()

        val favoriteTvShowEntities =
            Resource.success(PagedListTestUtils.mockPagedList(DummyTvShows.generateDummyTvShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(favoriteTvShowEntities.data)
        assertEquals(tvShowsResponse.size.toLong(), favoriteTvShowEntities.data?.size?.toLong())
    }
}