package com.nazarhuliiev.movieapp.repository

import androidx.lifecycle.MutableLiveData

class MovieRepository {

    fun getMovie(id: Int) : MutableLiveData<LocalMovie>{

        return MutableLiveData(LocalMovie("El Camino", 2019, 7.6f));
    }

    fun getAllMovies() : MutableLiveData<List<LocalMovie>> {

        val movies = listOf(
            LocalMovie("Movie 1", 1, 5f),
            LocalMovie("Movie 1", 1, 5f),
            LocalMovie("Movie 1", 1, 5f),
            LocalMovie("Movie 1", 1, 5f),
            LocalMovie("Movie 1", 1, 5f),
            LocalMovie("Movie 1", 1, 5f),
            LocalMovie("Movie 1", 1, 5f),
            LocalMovie("Movie 1", 1, 5f));

        return MutableLiveData(movies);
    }
}