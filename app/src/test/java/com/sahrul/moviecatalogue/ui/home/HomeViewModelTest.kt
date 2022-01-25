package com.sahrul.moviecatalogue.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.sahrul.moviecatalogue.data.Repository
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.utils.SortUtils
import com.sahrul.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val sort = SortUtils.NEWEST

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var tvShowsObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var moviesPagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowsPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(moviesPagedList)
        `when`(dummyMovies.data?.size).thenReturn(2)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getMovies(sort)).thenReturn(movies)
        val movieEntities = viewModel.getMovies(sort).value?.data
        verify(repository).getMovies(sort)
        assertNotNull(movieEntities)
        assertEquals(2, movieEntities?.size)

        viewModel.getMovies(sort).observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = Resource.success(tvShowsPagedList)
        `when`(dummyTvShows.data?.size).thenReturn(2)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(repository.getTvShows(sort)).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows(sort).value?.data
        verify(repository).getTvShows(sort)
        assertNotNull(tvShowEntities)
        assertEquals(2, tvShowEntities?.size)

        viewModel.getTvShows(sort).observeForever(tvShowsObserver)
        verify(tvShowsObserver).onChanged(dummyTvShows)
    }
}