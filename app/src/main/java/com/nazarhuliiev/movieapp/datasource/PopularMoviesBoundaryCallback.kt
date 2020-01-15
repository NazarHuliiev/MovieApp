package com.nazarhuliiev.movieapp.datasource

import androidx.paging.PagedList
import com.nazarhuliiev.movieapp.repository.movie.Movie
import com.nazarhuliiev.movieapp.repository.movie.MovieRepository
import com.nazarhuliiev.movieapp.service.movie.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMoviesBoundaryCallback(
    private val repository: MovieRepository,
    private val movieService: MovieService,
    private val scope: CoroutineScope
) : PagedList.BoundaryCallback<Movie>() {

    private var isRequestInProgress = false

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        requestAndSaveData(itemAtEnd.page + 1)
    }

    override fun onZeroItemsLoaded() {
        requestAndSaveData(1)
    }

    private fun requestAndSaveData(page: Int) {

        if (isRequestInProgress) return

        isRequestInProgress = true

        scope.launch {
            withContext(Dispatchers.IO) {
                val result = movieService.getPopularMovies(page)
                repository.saveMovies(page, result.results)
            }

            isRequestInProgress = false
        }
    }
}