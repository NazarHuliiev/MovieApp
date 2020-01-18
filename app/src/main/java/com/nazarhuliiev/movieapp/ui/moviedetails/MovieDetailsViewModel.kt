package com.nazarhuliiev.movieapp.ui.moviedetails

import androidx.lifecycle.ViewModel
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository

class MovieDetailsViewModel(private val movieRepository: MovieRepository): ViewModel() {
    var movieId: Int? = null

    val movie by lazy {
        movieRepository.getMoviie(movieId ?: -1)
    }
}