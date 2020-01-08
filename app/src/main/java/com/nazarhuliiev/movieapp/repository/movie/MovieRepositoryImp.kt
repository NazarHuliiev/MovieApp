package com.nazarhuliiev.movieapp.repository.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nazarhuliiev.movieapp.db.MoviesDatabase
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovies
import com.nazarhuliiev.movieapp.service.movie.MovieService

class MovieRepositoryImp(
    private val movieService: MovieService,
    private val moviesDatabase: MoviesDatabase
): MovieRepository {

    override fun getMovie(id: Int): LiveData<Movie> {
        return MutableLiveData(
            Movie(
                "El Camino",
                2019,
                7.6f
            )
        )
    }

    override fun getPopularMovies(page: Int): List<Movie> {
        return mapMovies(movieService.getPopularMovies(page).execute().body() as RemotePopularMovies)
    }

    private fun mapMovies(remotePopularMovies: RemotePopularMovies): List<Movie> {
        val list = mutableListOf<Movie>()

        for (remoteMovie in remotePopularMovies.results) {
            val m = Movie(
                remoteMovie.title,
                remoteMovie.releaseDate.year,
                remoteMovie.voteAverage.toFloat()
            )
            list.add(m)
        }

        return list
    }
}