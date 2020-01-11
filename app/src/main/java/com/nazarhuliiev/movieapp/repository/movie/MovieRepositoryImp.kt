package com.nazarhuliiev.movieapp.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nazarhuliiev.movieapp.datasource.PopularMoviesDataSource
import com.nazarhuliiev.movieapp.db.MoviesDatabase
import com.nazarhuliiev.movieapp.helpers.toMovie
import com.nazarhuliiev.movieapp.service.movie.MovieService
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovies

class MovieRepositoryImp(
    private val movieService: MovieService,
    moviesDatabase: MoviesDatabase
): MovieRepository {

    private val popularMoviesDao = moviesDatabase.popularMoviesDao()

    override fun observePagedPopularMovies(connectivityAvailable: Boolean)
            : LiveData<PagedList<Movie>> {
        if(connectivityAvailable) {
            return observeRemotePagedPopularMovies()
        }

        return observeLocalPagedPopularMovies()
    }

    private fun observeLocalPagedPopularMovies(): LiveData<PagedList<Movie>> {
         val dataSourceFactory = popularMoviesDao.getAllMovies().map{ it.toMovie()}

        return LivePagedListBuilder(
            dataSourceFactory,
            PopularMoviesDataSource.pagedListConfig())
            .build()
    }

    private fun observeRemotePagedPopularMovies(): LiveData<PagedList<Movie>> {
        val factory = PopularMoviesDataSource.Factory(
            popularMoviesDao
        ) { page ->
            (movieService.getPopularMovies(page).execute().body() as RemotePopularMovies).results
        }

        return LivePagedListBuilder<Int, Movie>(
            factory,
            PopularMoviesDataSource.pagedListConfig())
            .build()
    }
}