package com.nazarhuliiev.movieapp.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.helpers.UrlHelper
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment: Fragment(R.layout.fragment_movie_details) {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel by viewModel<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.movieId = args.movieId

        viewModel.movie.observe(viewLifecycleOwner, Observer {

            movie_details_title.text = it.name
            movie_details_rating.text = it.rating.toString()
            movie_details_year.text = it.year.toString()
            movie_details_overview.text = it.overview

            val circularProgressDrawable = CircularProgressDrawable(context!!)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 80f
            circularProgressDrawable.start()
            Glide
                .with(this)
                .load(UrlHelper.getImagePath(it.posterPath))
                .placeholder(circularProgressDrawable)
                .into(movie_details_image)
        })

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}