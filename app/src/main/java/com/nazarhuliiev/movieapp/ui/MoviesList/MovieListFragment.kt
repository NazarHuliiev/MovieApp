package com.nazarhuliiev.movieapp.ui.MoviesList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.nazarhuliiev.movieapp.R
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private val viewModel: MovieListViewModel by viewModels()
    private  val adapter = MoviesRecyclerAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesList.layoutManager = GridLayoutManager(context, 2)
        moviesList.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}