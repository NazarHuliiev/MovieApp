package com.nazarhuliiev.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nazarhuliiev.movieapp.BuildConfig

class MovieRepository {

    private val wikiApiServe by lazy {
        TheMovieDatabaseApiService.create()
    }

    fun getMovie(id: Int) : LiveData<Movie> {
        return MutableLiveData(Movie("El Camino", 2019, 7.6f))
    }

    suspend fun getAllMovies() : List<Movie> {
        return mapMovies(wikiApiServe.getPopularMovies(BuildConfig.API_KEY))
    }

    private fun mapMovies(remotePopularMovies: RemotePopularMovies): List<Movie>{
        val list = mutableListOf<Movie>()

        for (remoteMovie in remotePopularMovies.results){
            val m = Movie(remoteMovie.title, remoteMovie.releaseDate.year, remoteMovie.voteAverage.toFloat())
            list.add(m)
        }

        return list
    }
}