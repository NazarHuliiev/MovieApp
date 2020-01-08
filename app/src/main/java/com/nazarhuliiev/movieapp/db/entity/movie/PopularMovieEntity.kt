package com.nazarhuliiev.movieapp.db.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

@Entity(tableName = "popular_movies")
data class PopularMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: LocalDate,
    val genreIds : List<Int>,
    val voteAverage : Double,
    val page: Int)