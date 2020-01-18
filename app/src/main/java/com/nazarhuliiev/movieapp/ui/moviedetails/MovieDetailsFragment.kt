package com.nazarhuliiev.movieapp.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nazarhuliiev.movieapp.R
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

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}