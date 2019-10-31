package com.nazarhuliiev.movieapp.repository

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDatabaseApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api_key: String): Observable<RemotePopularMovies>

    companion object{
        fun create(): TheMovieDatabaseApiService{

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()

            return retrofit.create(TheMovieDatabaseApiService::class.java)
        }
    }
}