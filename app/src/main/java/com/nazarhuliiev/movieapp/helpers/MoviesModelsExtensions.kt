package com.nazarhuliiev.movieapp.helpers

import com.nazarhuliiev.movieapp.db.entity.movie.PopularMovieEntity
import com.nazarhuliiev.movieapp.repository.movie.Movie
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovie

fun List<RemotePopularMovie>.toLocal(page: Int): List<PopularMovieEntity> {
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
                remoteMovie.popularity,
                page)
        )
    }

    return localMovies
}

fun PopularMovieEntity.toMovie(): Movie{
    return Movie(
        this.id,
        this.title,
        this.overview,
        this.releaseDate.year,
        this.voteAverage.toFloat(),
        this.page,
        this.posterPath
    )
}