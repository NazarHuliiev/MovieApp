package com.nazarhuliiev.movieapp.repository

import com.nazarhuliiev.movieapp.BuildConfig
import com.nazarhuliiev.movieapp.repository.httpclient.HttpClientFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TheMovieDatabaseApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): RemotePopularMovies

    companion object{
        fun create(): TheMovieDatabaseApiService{

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .client(HttpClientFactory.create())
                .build()

            return retrofit.create(TheMovieDatabaseApiService::class.java)
        }
    }
}