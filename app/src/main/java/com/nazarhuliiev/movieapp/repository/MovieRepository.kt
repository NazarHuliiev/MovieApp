package com.nazarhuliiev.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieRepository {

    private val wikiApiServe by lazy {
        TheMovieDatabaseApiService.create()
    }

    fun getMovie(id: Int) : LiveData<Movie> {
        return MutableLiveData(Movie("El Camino", 2019, 7.6f))
    }

    suspend fun getAllMovies() : List<Movie> {
        return mapMovies(wikiApiServe.getPopularMovies("bea5f6ac0e862f1b35f599b2d666959f"))
    }

    private fun mapMovies(remotePopularMovies: RemotePopularMovies): List<Movie>{
        val list = mutableListOf<Movie>()

        for (remoteMovie in remotePopularMovies.results){
            val m = Movie(remoteMovie.original_title, remoteMovie.release_date.year, remoteMovie.vote_average.toFloat())
            list.add(m)
        }

        return list
    }
}