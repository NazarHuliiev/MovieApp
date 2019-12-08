package com.nazarhuliiev.movieapp.ui.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nazarhuliiev.movieapp.repository.movie.Movie
import java.lang.RuntimeException

class MoviesRecyclerAdapter: PagedListAdapter<Movie, MovieViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(getItem(position) ?: throw RuntimeException("This adapter does not support placeholders"))
    }

    class DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}