package com.nazarhuliiev.movieapp.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazarhuliiev.movieapp.db.entity.movie.PopularMovieEntity

@Dao
interface PopularMoviesDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertMovies(list: List<PopularMovieEntity>)

    @Query("SELECT * FROM popular_movies ORDER BY page ASC")
    fun getAllMovies(): DataSource.Factory<Int, PopularMovieEntity>

    @Query("SELECT * FROM popular_movies WHERE id = :id")
    fun getMovieById(id: Int): LiveData<PopularMovieEntity>

    @Query("DELETE FROM popular_movies")
    fun deleteMovies()
}