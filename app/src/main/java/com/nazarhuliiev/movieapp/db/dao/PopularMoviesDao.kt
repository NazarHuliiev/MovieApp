package com.nazarhuliiev.movieapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazarhuliiev.movieapp.db.entity.movie.PopularMovieEntity

@Dao
interface PopularMoviesDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertMoviestOnPage(list: List<PopularMovieEntity>)

    @Query("SELECT * FROM popular_movies")
    fun getAllMovies(): List<PopularMovieEntity>

    @Query("SELECT * FROM popular_movies WHERE page = :page")
    fun getMoviesOnPage(page: Int): List<PopularMovieEntity>

    @Query("DELETE FROM popular_movies WHERE page = :page")
    fun deleteMoviesOnPage(page: Int)
}