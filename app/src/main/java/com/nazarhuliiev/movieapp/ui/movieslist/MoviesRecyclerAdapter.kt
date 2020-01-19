package com.nazarhuliiev.movieapp.ui.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nazarhuliiev.movieapp.repository.movie.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesRecyclerAdapter: PagedListAdapter<Movie, MovieViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    lateinit var itemClickEvent: (imageView: ImageView, data: Movie) -> Unit

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position) ?: throw RuntimeException("This adapter does not support placeholders")

        holder.itemView.setOnClickListener {
            itemClickEvent?.invoke(holder.itemView.movie_image, item)
        }

        holder.bindData(item)
    }

    class DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}