package com.nazarhuliiev.movieapp.ui.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movies by lazy {
        movieRepository.observePagedPopularMovies(viewModelScope)
    }
}