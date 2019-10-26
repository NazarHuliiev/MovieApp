package com.nazarhuliiev.movieapp.ui.MoviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.repository.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie, parent, false)) {

    private val movieName: TextView = itemView.movieName
    private val movieYear: TextView = itemView.movieYear
    private val movieRating: TextView = itemView.movieRating

    fun bindData (movie: Movie) {
        movieName.text = movie.name
        movieRating.text = movie.rating.toString()
        movieYear.text = movie.year.toString()
    }
}