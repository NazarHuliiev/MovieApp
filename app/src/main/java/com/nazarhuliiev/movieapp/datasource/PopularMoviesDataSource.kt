package com.nazarhuliiev.movieapp.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.nazarhuliiev.movieapp.repository.movie.Movie
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository

class PopularMoviesDataSource private constructor(
    private val movieRepository: MovieRepository)
    : PageKeyedDataSource<Int, Movie>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val page = movieRepository.getPopularMovies(1)
        callback.onResult(page, 1, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = movieRepository.getPopularMovies(params.key)
        callback.onResult(page, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // this should be empty because there is no need to load data before
    }

    class Factory(
        private val movieRepository: MovieRepository)
        : DataSource.Factory<Int, Movie>() {
        override fun create() =
            PopularMoviesDataSource(movieRepository)
    }
}