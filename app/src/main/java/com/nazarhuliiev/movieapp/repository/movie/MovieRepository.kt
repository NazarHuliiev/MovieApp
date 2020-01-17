package com.nazarhuliiev.movieapp.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovie
import kotlinx.coroutines.CoroutineScope

interface MovieRepository {
    fun observePagedPopularMovies(scope: CoroutineScope): LiveData<PagedList<Movie>>

    fun saveMovies(page: Int, remoteMovies: List<RemotePopularMovie>)
}