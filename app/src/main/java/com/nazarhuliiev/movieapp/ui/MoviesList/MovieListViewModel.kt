package com.nazarhuliiev.movieapp.ui.MoviesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nazarhuliiev.movieapp.repository.Movie
import com.nazarhuliiev.movieapp.repository.MovieRepository

class MovieListViewModel : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()

    val movies: MutableLiveData<List<Movie>> by lazy {

       movieRepository.getAllMovies()
    }
}