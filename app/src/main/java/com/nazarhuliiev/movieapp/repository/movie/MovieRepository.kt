package com.nazarhuliiev.movieapp.repository.movie

import androidx.lifecycle.LiveData

interface MovieRepository {

    fun getMovie(id: Int) : LiveData<Movie>

    fun getMovies(page: Int) : List<Movie>
}