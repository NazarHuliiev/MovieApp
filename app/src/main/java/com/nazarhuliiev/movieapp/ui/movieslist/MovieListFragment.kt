package com.nazarhuliiev.movieapp.ui.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.nazarhuliiev.movieapp.R
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private val viewModel by viewModel<MovieListViewModel>()
    private  val adapter = MoviesRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movies_list.layoutManager = GridLayoutManager(context, 2)
        movies_list.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        adapter.itemClickEvent = { imageView, data ->
            run {
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
    }
}