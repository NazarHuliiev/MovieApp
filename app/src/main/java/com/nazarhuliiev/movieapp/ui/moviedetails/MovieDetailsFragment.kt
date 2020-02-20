package com.nazarhuliiev.movieapp.ui.moviedetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.nazarhuliiev.movieapp.GlideApp
import com.nazarhuliiev.movieapp.R
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment: Fragment(R.layout.fragment_movie_details) {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel by viewModel<MovieDetailsViewModel>()
    private var rating = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel.movieId = args.movieId

        loadDetailsImage()

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            movie_details_title.text = it.name
            movie_details_rating.text = it.rating.toString()
            movie_details_year.text = it.year.toString()
            movie_details_overview.text = it.overview
            movie_details_rating_view.setRating(it.rating)
        })
    }

    private fun loadDetailsImage() {
        val imageUrl = args.posterPath
        movie_details_image.also {
            it.transitionName = imageUrl

            GlideApp
                .with(context!!)
                .load(imageUrl)
                .into(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}