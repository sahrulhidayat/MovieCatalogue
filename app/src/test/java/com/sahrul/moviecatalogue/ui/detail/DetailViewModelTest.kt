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
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DummyMovies.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = DummyTvShows.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

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
        val resultMovie = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = resultMovie

        `when`(repository.getMovieDetails(movieId)).thenReturn(movie)
        val movieDetails = viewModel.getMovieDetails(movieId).value?.data
        verify(repository).getMovieDetails(movieId)
        assertNotNull(movieDetails)
        assertEquals(dummyMovie.id, movieDetails?.id)
        assertEquals(dummyMovie.image, movieDetails?.image)
        assertEquals(dummyMovie.title, movieDetails?.title)
        assertEquals(dummyMovie.overview, movieDetails?.overview)
        assertEquals(dummyMovie.release, movieDetails?.release)
        assertEquals(dummyMovie.ratings, movieDetails?.ratings)
        assertEquals(dummyMovie.category, movieDetails?.category)

        viewModel.getMovieDetails(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(resultMovie)
    }

    @Test
    fun getTvShowDetails() {
        val resultTvShow = Resource.success(dummyTvShow)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = resultTvShow

        `when`(repository.getTvShowDetails(tvShowId)).thenReturn(tvShow)
        val tvShowDetails = viewModel.getTvShowDetails(tvShowId).value?.data
        verify(repository).getTvShowDetails(tvShowId)
        assertNotNull(tvShowDetails)
        assertEquals(dummyTvShow.id, tvShowDetails?.id)
        assertEquals(dummyTvShow.image, tvShowDetails?.image)
        assertEquals(dummyTvShow.title, tvShowDetails?.title)
        assertEquals(dummyTvShow.overview, tvShowDetails?.overview)
        assertEquals(dummyTvShow.release, tvShowDetails?.release)
        assertEquals(dummyTvShow.ratings, tvShowDetails?.ratings)
        assertEquals(dummyTvShow.category, tvShowDetails?.category)

        viewModel.getTvShowDetails(tvShowId).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(resultTvShow)
    }
}