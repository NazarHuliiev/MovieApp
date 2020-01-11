package com.nazarhuliiev.movieapp.di

import com.nazarhuliiev.movieapp.db.getDatabase
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository
import com.nazarhuliiev.movieapp.repository.movie.MovieRepositoryImp
import com.nazarhuliiev.movieapp.ui.movieslist.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<MovieRepository> {
        MovieRepositoryImp(
            get(),
            get()
        )
    }

    single { getDatabase(get()).popularMoviesDao() }

    viewModel { MovieListViewModel(get()) }
}