package com.sahrul.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.sahrul.moviecatalogue.data.Repository
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.utils.DummyMovies
import com.sahrul.moviecatalogue.utils.DummyTvShows
import com.sahrul.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovies = DummyMovies.generateDummyMovies()[0]
    private val movieId = dummyMovies.id
    private val dummyTvShows = DummyTvShows.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShows.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieDetails() {
        val movieDetails = Resource.success(dummyMovies)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = movieDetails

        `when`(repository.getMovieDetails(movieId)).thenReturn(movie)
        verify(repository).getMovieDetails(movieId)
        viewModel.getMovieDetails(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(movieDetails)
    }

    @Test
    fun getTvShowDetails() {
        val tvShowDetails = Resource.success(dummyTvShows)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = tvShowDetails

        `when`(repository.getTvShowDetails(tvShowId)).thenReturn(tvShow)
        verify(repository).getTvShowDetails(tvShowId)
        viewModel.getTvShowDetails(tvShowId).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowDetails)
    }
}