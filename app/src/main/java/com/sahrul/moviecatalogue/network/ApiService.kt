package com.sahrul.moviecatalogue.network

import com.sahrul.moviecatalogue.BuildConfig
import com.sahrul.moviecatalogue.data.source.remote.response.MovieDetailsResponse
import com.sahrul.moviecatalogue.data.source.remote.response.MoviesResponse
import com.sahrul.moviecatalogue.data.source.remote.response.TvShowDetailsResponse
import com.sahrul.moviecatalogue.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("3/movie/popular?api_key=${BuildConfig.TMDB_TOKEN}&language=en-US&page=1")
    fun getMovies(): Call<MoviesResponse>

    @GET("3/tv/popular?api_key=${BuildConfig.TMDB_TOKEN}&language=en-US&page=1")
    fun getTvShows(): Call<TvShowsResponse>

    @GET("3/movie/{id}?api_key=${BuildConfig.TMDB_TOKEN}&language=en-US")
    fun getMovieDetails(
        @Path("id") id: Int
    ): Call<MovieDetailsResponse>

    @GET("3/tv/{id}?api_key=${BuildConfig.TMDB_TOKEN}&language=en-US")
    fun getTvShowDetails(
        @Path("id") id: Int
    ): Call<TvShowDetailsResponse>
}