package com.nazarhuliiev.movieapp.repository.movie

import retrofit2.http.GET

interface TheMovieDatabaseApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): RemotePopularMovies
}