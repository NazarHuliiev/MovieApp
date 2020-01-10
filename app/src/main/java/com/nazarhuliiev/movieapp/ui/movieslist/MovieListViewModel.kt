package com.nazarhuliiev.movieapp.ui.movieslist

import androidx.lifecycle.ViewModel
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var networkAvailable: Boolean = false

    val movies by lazy {
        movieRepository.observePagedPopularMovies(networkAvailable)
    }
}