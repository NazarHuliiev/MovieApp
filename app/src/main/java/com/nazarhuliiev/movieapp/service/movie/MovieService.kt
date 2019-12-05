package com.nazarhuliiev.movieapp.service.movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<RemotePopularMovies>
}