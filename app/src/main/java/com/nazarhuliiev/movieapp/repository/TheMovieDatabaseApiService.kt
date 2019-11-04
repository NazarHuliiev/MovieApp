package com.nazarhuliiev.movieapp.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDatabaseApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String): RemotePopularMovies

    companion object{
        fun create(): TheMovieDatabaseApiService{

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()

            return retrofit.create(TheMovieDatabaseApiService::class.java)
        }
    }
}