package com.nazarhuliiev.movieapp.ui.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.repository.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie, parent, false)) {

    fun bindData (movie: Movie) {
        itemView.movieName.text = movie.name
        itemView.movieRating.text = movie.rating.toString()
        itemView.movieYear.text = movie.year.toString()
    }
}