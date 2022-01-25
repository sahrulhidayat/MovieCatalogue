package com.sahrul.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sahrul.moviecatalogue.data.source.remote.response.*
import com.sahrul.moviecatalogue.network.ApiConfig
import com.sahrul.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovies(): LiveData<ApiResponse<List<ResultsMovie>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ResultsMovie>>>()

        val client = ApiConfig.getApiService().getMovies()
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val result = response.body()?.results
                if (result != null ) resultMovies.postValue(ApiResponse.success(result))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getMovieDetails(movieId: Int): LiveData<ApiResponse<MovieDetailsResponse>> {
        EspressoIdlingResource.increment()
        val movieDetails = MutableLiveData<ApiResponse<MovieDetailsResponse>>()

        val client = ApiConfig.getApiService().getMovieDetails(movieId)
        client.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                val result = response.body()
                if (result != null) movieDetails.postValue(ApiResponse.success(result))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return movieDetails
    }

    fun getTvShows(): LiveData<ApiResponse<List<ResultsTvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<ResultsTvShow>>>()

        val client = ApiConfig.getApiService().getTvShows()
        client.enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                val result = response.body()?.results
                if (result != null) resultTvShows.postValue(ApiResponse.success(result))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

    fun getTvShowDetails(tvShowId: Int): LiveData<ApiResponse<TvShowDetailsResponse>> {
        EspressoIdlingResource.increment()
        val tvShowDetails = MutableLiveData<ApiResponse<TvShowDetailsResponse>>()

        val client = ApiConfig.getApiService().getTvShowDetails(tvShowId)
        client.enqueue(object : Callback<TvShowDetailsResponse> {
            override fun onResponse(
                call: Call<TvShowDetailsResponse>,
                response: Response<TvShowDetailsResponse>
            ) {
                val result = response.body()
                if (result != null) tvShowDetails.postValue(ApiResponse.success(result))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return tvShowDetails
    }

    companion object {
        private val TAG: String = RemoteDataSource::class.java.simpleName
    }
}