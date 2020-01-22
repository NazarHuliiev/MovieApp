package com.nazarhuliiev.movieapp.repository.movie

data class Movie(
    val id: Int,
    val name: String,
    val overview: String,
    val year: Int,
    val rating: Float,
    val page: Int,
    val posterPath: String)