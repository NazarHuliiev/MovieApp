package com.nazarhuliiev.movieapp.helpers

import com.nazarhuliiev.movieapp.db.entity.movie.PopularMovieEntity
import com.nazarhuliiev.movieapp.repository.movie.Movie
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovie

fun List<RemotePopularMovie>.remoteToMovie(): List<Movie>{
    val movies = mutableListOf<Movie>()

    for (remoteMovie in this) {
        val m = Movie(
            remoteMovie.title,
            remoteMovie.releaseDate.year,
            remoteMovie.voteAverage.toFloat()
        )
        movies.add(m)
    }

    return movies
}

fun List<RemotePopularMovie>.toLocal(): List<PopularMovieEntity> {
    val localMovies = mutableListOf<PopularMovieEntity>()

    for (remoteMovie in this) {
        localMovies.add(
            PopularMovieEntity(
                remoteMovie.id,
                remoteMovie.title,
                remoteMovie.overview,
                remoteMovie.posterPath,
                remoteMovie.releaseDate,
                remoteMovie.genreIds,
                remoteMovie.voteAverage,
                remoteMovie.popularity)
        )
    }

    return localMovies
}

fun PopularMovieEntity.toMovie(): Movie{
    return Movie(
        this.title,
        this.releaseDate.year,
        this.voteAverage.toFloat()
    )
}