package com.nazarhuliiev.movieapp.ui.MoviesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nazarhuliiev.movieapp.repository.LocalMovie
import com.nazarhuliiev.movieapp.repository.MovieRepository

class MovieListViewModel : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()

    val movies: MutableLiveData<List<LocalMovie>> by lazy {

       movieRepository.getAllMovies()
    }
}