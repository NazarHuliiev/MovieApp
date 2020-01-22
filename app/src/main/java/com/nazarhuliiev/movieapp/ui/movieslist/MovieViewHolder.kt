package com.nazarhuliiev.movieapp.ui.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.nazarhuliiev.movieapp.GlideApp
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.repository.movie.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(
    inflater: LayoutInflater,
    private val parent: ViewGroup,
    private val itemClickEvent: (imageView: ImageView, data: Movie) -> Unit)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie, parent, false)) {

    private var circularProgressDrawable: CircularProgressDrawable = CircularProgressDrawable(parent.context)
    private lateinit var movie: Movie

    init {
        circularProgressDrawable.strokeWidth = itemView.context.resources.getDimensionPixelSize(R.dimen.movie_loader_stroke_width).toFloat()
        circularProgressDrawable.centerRadius = itemView.context.resources.getDimensionPixelSize(R.dimen.movie_loader_center_radius).toFloat()
        circularProgressDrawable.start()

        itemView.setOnClickListener {
            itemClickEvent.invoke(itemView.movie_image, movie)
        }
    }

    fun bindData (movie: Movie) {

        this.movie = movie

        itemView.movie_name.text = movie.name
        itemView.movie_rating.text = movie.rating.toString()
        itemView.movie_year.text = movie.year.toString()

        itemView.movie_image.also {
            it.transitionName = movie.posterPath

            GlideApp.with(parent)
                .load(movie.posterPath)
                .placeholder(circularProgressDrawable)
                .into(it)
        }
    }
}