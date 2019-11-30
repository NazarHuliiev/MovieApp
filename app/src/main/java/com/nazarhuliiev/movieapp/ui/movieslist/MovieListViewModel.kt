package com.nazarhuliiev.movieapp.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nazarhuliiev.movieapp.repository.Movie
import com.nazarhuliiev.movieapp.repository.MovieRepository
import com.nazarhuliiev.movieapp.repository.MovieRepositoryImp

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movies: LiveData<List<Movie>> = liveData {
        val data = movieRepository.getAllMovies()
        emit(data)
    }
}