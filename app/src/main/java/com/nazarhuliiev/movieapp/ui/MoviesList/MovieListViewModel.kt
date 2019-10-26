package com.nazarhuliiev.movieapp.ui.MoviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nazarhuliiev.movieapp.repository.Movie
import com.nazarhuliiev.movieapp.repository.MovieRepository

class MovieListViewModel : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()

    val movies: LiveData<List<Movie>> by lazy {

       movieRepository.getAllMovies()
    }
}