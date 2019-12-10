package com.nazarhuliiev.movieapp.di

import com.nazarhuliiev.movieapp.BuildConfig
import com.nazarhuliiev.movieapp.service.gson.GsonFactory
import com.nazarhuliiev.movieapp.service.httpclient.HttpClientFactory
import com.nazarhuliiev.movieapp.service.movie.MovieService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { HttpClientFactory.create() }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(MovieService::class.java) }
}