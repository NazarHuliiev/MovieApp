package com.nazarhuliiev.movieapp.ui.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.helpers.UrlHelper
import com.nazarhuliiev.movieapp.repository.movie.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(
    inflater: LayoutInflater,
    private val parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie, parent, false)) {

    fun bindData (movie: Movie) {
        itemView.movie_name.text = movie.name
        itemView.movie_rating.text = movie.rating.toString()
        itemView.movie_year.text = movie.year.toString()

        val circularProgressDrawable = CircularProgressDrawable(parent.context)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 80f
        circularProgressDrawable.start()

        itemView.movie_image.also {
            it.transitionName = movie.posterPath

            Glide
                .with(parent)
                .load(movie.posterPath)
                .placeholder(circularProgressDrawable)
                .into(it)
        }
    }
}