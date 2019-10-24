package com.nazarhuliiev.movieapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nazarhuliiev.movieapp.repository.LocalMovie
import com.nazarhuliiev.movieapp.repository.MovieRepository

class MovieListViewModel : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()

    val movie: MutableLiveData<LocalMovie> by lazy {

       movieRepository.getMovie()
    }
}