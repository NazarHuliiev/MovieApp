package com.nazarhuliiev.movieapp.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

interface MovieRepository {
    fun observePagedPopularMovies(connectivityAvailable: Boolean): LiveData<PagedList<Movie>>
}