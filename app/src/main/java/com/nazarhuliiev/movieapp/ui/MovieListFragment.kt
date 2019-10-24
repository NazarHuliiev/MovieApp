package com.nazarhuliiev.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nazarhuliiev.movieapp.R
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private val viewModel: MovieListViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movie.observe(this, Observer {
            movieName.text = it.name
            movieYear.text = it.year.toString()
            movieRating.text = it.rating.toString()
        })
    }

}