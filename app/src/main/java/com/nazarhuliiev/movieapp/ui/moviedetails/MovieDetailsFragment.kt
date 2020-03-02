package com.nazarhuliiev.movieapp.ui.moviedetails

import android.location.Geocoder
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nazarhuliiev.movieapp.GlideApp
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.helpers.toBitmapDescriptor
import com.nazarhuliiev.movieapp.views.ScrollAwareSupportMapFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment: Fragment(R.layout.fragment_movie_details), OnMapReadyCallback {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel by viewModel<MovieDetailsViewModel>()
    private lateinit var geocoder: Geocoder
    private lateinit var mapFragment: ScrollAwareSupportMapFragment
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)

        geocoder = Geocoder(context)
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
            movie_details_overview.text = it.overview
            movie_details_rating_view.setRating(it.rating)
            searchCountry(it.country)
        })

        mapFragment = childFragmentManager.findFragmentById(R.id.movie_details_map) as ScrollAwareSupportMapFragment
        mapFragment.getMapAsync(this)
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

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map

        mapFragment.setListener {
            movie_details_content_scroll_view.requestDisallowInterceptTouchEvent(true)
        }
    }

    private fun searchCountry(countryName: String) {
        if(googleMap == null) {
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {

            val addresses = withContext(Dispatchers.IO) {
                geocoder.getFromLocationName(countryName, 1)
            }

            if(addresses != null) {
                val address = addresses[0]
                val point = LatLng(address.latitude, address.longitude)

                googleMap!!.addMarker(MarkerOptions().position(point)
                    .title(address.featureName)
                    .icon(requireContext().getDrawable(R.drawable.ic_movie_filter).toBitmapDescriptor()))

                val cameraPosition = CameraPosition.Builder().target(point).zoom(5f).build()
                googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        }
    }
}