package com.nazarhuliiev.movieapp.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nazarhuliiev.movieapp.repository.movie.Movie
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository
import com.nazarhuliiev.movieapp.datasource.PopularMoviesDataSource

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var movies: LiveData<PagedList<Movie>>
    init {
        val factory = PopularMoviesDataSource.Factory(movieRepository)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        movies = LivePagedListBuilder<Int, Movie>(factory, config).build()
    }
}