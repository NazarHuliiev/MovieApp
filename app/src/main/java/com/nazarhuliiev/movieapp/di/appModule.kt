package com.nazarhuliiev.movieapp.di

import androidx.room.Room
import com.nazarhuliiev.movieapp.db.MoviesDatabase
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository
import com.nazarhuliiev.movieapp.repository.movie.MovieRepositoryImp
import com.nazarhuliiev.movieapp.ui.moviedetails.MovieDetailsFragment
import com.nazarhuliiev.movieapp.ui.moviedetails.MovieDetailsViewModel
import com.nazarhuliiev.movieapp.ui.movieslist.MovieListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<MovieRepository> {
        MovieRepositoryImp(
            get(),
            get()
        )
    }

    single {
        Room
        .databaseBuilder(
            androidApplication(),
            MoviesDatabase::class.java,
            "movies_db")
        .build() }

    single { get<MoviesDatabase>().popularMoviesDao() }

    viewModel { MovieListViewModel(get()) }
    viewModel { MovieDetailsViewModel() }
}