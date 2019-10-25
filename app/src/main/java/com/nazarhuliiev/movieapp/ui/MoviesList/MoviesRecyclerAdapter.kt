package com.nazarhuliiev.movieapp.ui.MoviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nazarhuliiev.movieapp.repository.LocalMovie

class MoviesRecyclerAdapter: ListAdapter<LocalMovie, MovieViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class DiffCallback: DiffUtil.ItemCallback<LocalMovie>() {
        override fun areItemsTheSame(oldItem: LocalMovie, newItem: LocalMovie): Boolean {
            return oldItem.name == newItem.name && oldItem.year == newItem.year
        }

        override fun areContentsTheSame(oldItem: LocalMovie, newItem: LocalMovie): Boolean {
            return oldItem == newItem
        }
    }
}