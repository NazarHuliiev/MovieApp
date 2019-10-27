package com.nazarhuliiev.movieapp.ui.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.repository.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie, parent, false)) {

    fun bindData (movie: Movie) {
        itemView.movie_name.text = movie.name
        itemView.movie_rating.text = movie.rating.toString()
        itemView.movie_year.text = movie.year.toString()
    }
}