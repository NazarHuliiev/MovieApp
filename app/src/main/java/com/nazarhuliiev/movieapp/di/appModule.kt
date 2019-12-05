package com.nazarhuliiev.movieapp.di

import com.nazarhuliiev.movieapp.BuildConfig
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository
import com.nazarhuliiev.movieapp.repository.movie.MovieRepositoryImp
import com.nazarhuliiev.movieapp.service.movie.MovieService
import com.nazarhuliiev.movieapp.service.gson.GsonFactory
import com.nazarhuliiev.movieapp.service.httpclient.HttpClientFactory
import com.nazarhuliiev.movieapp.ui.movieslist.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { HttpClientFactory.create() }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(MovieService::class.java) }

    single<MovieRepository> {
        MovieRepositoryImp(
            get()
        )
    }

    viewModel { MovieListViewModel(get()) }
}