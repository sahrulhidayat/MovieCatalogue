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
        val movieDetailsEntity = LiveDataTestUtil.getValue(repository.getMovieDetails(movieId)).data
        verify(local).getMovieDetails(eq(movieId))
        assertNotNull(movieDetailsEntity)
        if (movieDetailsEntity != null) {
            assertEquals(movieDetailsResponse.id, movieDetailsEntity.id)
            assertEquals(movieDetailsResponse.posterPath, movieDetailsEntity.image)
            assertEquals(movieDetailsResponse.title, movieDetailsEntity.title)
            assertEquals(movieDetailsResponse.overview, movieDetailsEntity.overview)
            assertEquals(movieDetailsResponse.releaseDate, movieDetailsEntity.release)
            assertEquals(
                movieDetailsResponse.voteAverage.toString(),
                movieDetailsEntity.ratings.toString()
            )
            assertEquals(
                movieCategoryFormatter(movieDetailsResponse.genres),
                movieDetailsEntity.category
            )
            assertEquals(
                durationFormatter(movieDetailsResponse.runtime),
                movieDetailsEntity.duration
            )
        }
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
        val tvShowEntity = LiveDataTestUtil.getValue(repository.getTvShowDetails(tvShowId)).data
        verify(local).getTvShowDetails(eq(tvShowId))
        assertNotNull(tvShowEntity)
        if (tvShowEntity != null) {
            assertEquals(tvShowDetailsResponse.id, tvShowEntity.id)
            assertEquals(tvShowDetailsResponse.posterPath, tvShowEntity.image)
            assertEquals(tvShowDetailsResponse.title, tvShowEntity.title)
            assertEquals(tvShowDetailsResponse.overview, tvShowEntity.overview)
            assertEquals(tvShowDetailsResponse.releaseDate, tvShowEntity.release)
            assertEquals(
                tvShowDetailsResponse.voteAverage.toString(),
                tvShowEntity.ratings.toString()
            )
            assertEquals(
                tvShowCategoryFormatter(tvShowDetailsResponse.genres),
                tvShowEntity.category
            )
        }
    }
}