package com.nazarhuliiev.movieapp.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nazarhuliiev.movieapp.repository.Movie
import com.nazarhuliiev.movieapp.repository.MovieRepository

class MovieListViewModel : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()
    private val movies = loadMovies()

    private fun loadMovies(): LiveData<List<Movie>>{
        return movieRepository.getAllMovies()
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }
}