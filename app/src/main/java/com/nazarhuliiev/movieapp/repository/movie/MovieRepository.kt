package com.nazarhuliiev.movieapp.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovie

interface MovieRepository {
    fun observePagedPopularMovies(connectivityAvailable: Boolean): LiveData<PagedList<Movie>>

    fun saveMovies(remoteMovies: List<RemotePopularMovie>)
}