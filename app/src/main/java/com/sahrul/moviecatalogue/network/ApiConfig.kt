package com.sahrul.moviecatalogue.network

import com.sahrul.moviecatalogue.BuildConfig
import com.sahrul.moviecatalogue.utils.Consts
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}