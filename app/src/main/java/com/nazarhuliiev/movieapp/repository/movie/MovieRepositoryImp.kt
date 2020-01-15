package com.nazarhuliiev.movieapp.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nazarhuliiev.movieapp.datasource.PopularMoviesBoundaryCallback
import com.nazarhuliiev.movieapp.db.dao.PopularMoviesDao
import com.nazarhuliiev.movieapp.helpers.toLocal
import com.nazarhuliiev.movieapp.helpers.toMovie
import com.nazarhuliiev.movieapp.service.movie.MovieService
import com.nazarhuliiev.movieapp.service.movie.RemotePopularMovie
import kotlinx.coroutines.CoroutineScope

class MovieRepositoryImp(
    private val movieService: MovieService,
    private val popularMoviesDao: PopularMoviesDao
): MovieRepository {

    override fun observePagedPopularMovies(scope: CoroutineScope)
            : LiveData<PagedList<Movie>> {


        val dataSourceFactory = popularMoviesDao.getAllMovies().map{ it.toMovie()}

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(
            dataSourceFactory,
            config)
            .setBoundaryCallback(PopularMoviesBoundaryCallback(this, movieService, scope))
            .build()
    }

    override fun saveMovies(page: Int, remoteMovies: List<RemotePopularMovie>) {
        popularMoviesDao.insertMovies(remoteMovies.toLocal(page))
    }
}