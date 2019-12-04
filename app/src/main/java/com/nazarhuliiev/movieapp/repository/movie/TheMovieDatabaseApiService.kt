package com.nazarhuliiev.movieapp.repository.movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDatabaseApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<RemotePopularMovies>
}