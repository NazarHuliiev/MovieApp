package com.nazarhuliiev.movieapp.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.nazarhuliiev.movieapp.helpers.remoteToMovie
import com.nazarhuliiev.movieapp.repository.movie.Movie
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovie

class PopularMoviesDataSource private constructor(
    private val repository: MovieRepository,
    private val fetchPopularMovies: (page: Int) ->List<RemotePopularMovie>)
    : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val page = fetchData(1)
        callback.onResult(page, 1, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = fetchData(params.key)
        callback.onResult(page, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // this should be empty because there is no need to load data before
    }

    private fun fetchData(page: Int): List<Movie>{
        val remotePopularMovies = fetchPopularMovies(page)
        repository.saveMovies(remotePopularMovies)

        return remotePopularMovies.remoteToMovie()
    }

    class Factory(
        private val repository: MovieRepository,
        private val fetchPopularMovies: (page: Int) ->List<RemotePopularMovie>)
        : DataSource.Factory<Int, Movie>() {
        override fun create() =
            PopularMoviesDataSource(repository, fetchPopularMovies)
    }

    companion object {
        fun pagedListConfig() = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()
    }
}