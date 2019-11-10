package com.nazarhuliiev.movieapp.repository

import com.google.gson.annotations.SerializedName

data class RemotePopularMovies (
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<RemotePopularMovie>,
    @SerializedName("total_results") val totalResults : Int,
    @SerializedName("total_pages") val totalPages : Int
)