package com.nazarhuliiev.movieapp.ui.movieslist

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.repository.movie.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val viewModel by viewModel<MovieListViewModel>()
    private val moviesAdapter = MoviesRecyclerAdapter(::onMovieClick)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupMoviesList()

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            moviesAdapter.submitList(it)
        })
    }

    private fun setupMoviesList() {
        movies_list.layoutManager = GridLayoutManager(context, 2)

        movies_list.apply {
            adapter = moviesAdapter
            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    private fun onMovieClick(imageView: ImageView, data: Movie) {
        val action = MovieListFragmentDirections
            .actionMovieListFragmentToMovieDetailsFragment(data.posterPath)
            .setMovieId(data.id)

        val extras = FragmentNavigatorExtras(
            imageView to data.posterPath
        )

        view?.findNavController()?.navigate(
            action,
            extras
        )
    }
}