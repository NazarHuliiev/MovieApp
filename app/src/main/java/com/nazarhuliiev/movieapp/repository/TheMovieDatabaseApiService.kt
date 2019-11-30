package com.nazarhuliiev.movieapp.repository

import retrofit2.http.GET

interface TheMovieDatabaseApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): RemotePopularMovies
}