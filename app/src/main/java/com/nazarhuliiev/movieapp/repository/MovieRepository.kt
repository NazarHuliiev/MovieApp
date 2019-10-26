package com.nazarhuliiev.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieRepository {

    fun getMovie(id: Int) : LiveData<Movie> {

        return MutableLiveData(Movie("El Camino", 2019, 7.6f));
    }

    fun getAllMovies() : LiveData<List<Movie>> {

        val movies = listOf(
            Movie("Movie 1", 1991, 5f),
            Movie("Movie 2", 1992, 1f),
            Movie("Movie 3", 1993, 3f),
            Movie("Movie 4", 1994, 5f),
            Movie("Movie 5", 1995, 1f),
            Movie("Movie 6", 1996, 3f),
            Movie("Movie 7", 1997, 5f),
            Movie("Movie 8", 1998, 5f));

        return MutableLiveData(movies);
    }
}