package com.nazarhuliiev.movieapp.repository

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class RemotePopularMovie (
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("release_date") val releaseDate : Date,
    @SerializedName("genre_ids") val genreIds : List<Int>,
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("vote_average") val voteAverage : Double
)