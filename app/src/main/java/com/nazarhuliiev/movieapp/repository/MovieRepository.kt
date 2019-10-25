package com.nazarhuliiev.movieapp.repository

import androidx.lifecycle.MutableLiveData

class MovieRepository {

    fun getMovie(id: Int) : MutableLiveData<LocalMovie>{

        return MutableLiveData(LocalMovie("El Camino", 2019, 7.6f));
    }

    fun getAllMovies() : MutableLiveData<List<LocalMovie>> {

        val movies = listOf(
            LocalMovie("Movie 1", 1991, 5f),
            LocalMovie("Movie 2", 1992, 1f),
            LocalMovie("Movie 3", 1993, 3f),
            LocalMovie("Movie 4", 1994, 5f),
            LocalMovie("Movie 5", 1995, 1f),
            LocalMovie("Movie 6", 1996, 3f),
            LocalMovie("Movie 7", 1997, 5f),
            LocalMovie("Movie 8", 1998, 5f));

        return MutableLiveData(movies);
    }
}