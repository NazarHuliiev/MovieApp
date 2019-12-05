package com.nazarhuliiev.movieapp.repository.movie

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class PopularMoviesDataSource private constructor(
    private val movieRepository: MovieRepository
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val page = movieRepository.getMovies(1)

        callback.onResult(page, 1, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        val page = movieRepository.getMovies(params.key)
        callback.onResult(page, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    class Factory(
        private val movieRepository: MovieRepository
    ) : DataSource.Factory<Int, Movie>() {

        override fun create() = PopularMoviesDataSource(movieRepository)
    }
}

