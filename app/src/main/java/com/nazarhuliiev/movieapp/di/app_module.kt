package com.nazarhuliiev.movieapp.di

import com.nazarhuliiev.movieapp.BuildConfig
import com.nazarhuliiev.movieapp.repository.MovieRepository
import com.nazarhuliiev.movieapp.repository.MovieRepositoryImp
import com.nazarhuliiev.movieapp.repository.TheMovieDatabaseApiService
import com.nazarhuliiev.movieapp.repository.gson.GsonFactory
import com.nazarhuliiev.movieapp.repository.httpclient.HttpClientFactory
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

    single { get<Retrofit>().create(TheMovieDatabaseApiService::class.java) }

    single<MovieRepository> { MovieRepositoryImp(get()) }

    viewModel { MovieListViewModel(get()) }
}