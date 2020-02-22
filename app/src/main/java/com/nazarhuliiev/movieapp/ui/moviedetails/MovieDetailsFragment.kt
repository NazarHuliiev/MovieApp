package com.nazarhuliiev.movieapp.ui.moviedetails

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nazarhuliiev.movieapp.GlideApp
import com.nazarhuliiev.movieapp.R
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment: Fragment(R.layout.fragment_movie_details), OnMapReadyCallback {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel by viewModel<MovieDetailsViewModel>()
    private lateinit var mapView: MapView
    private lateinit var geocoder: Geocoder
    private lateinit var countryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)

        geocoder = Geocoder(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = super.onCreateView(inflater, container, savedInstanceState)

        mapView = rootView!!.findViewById<MapView>(R.id.movie_details_map)

        mapView.onCreate(savedInstanceState)
        mapView.onResume()

        try {
            MapsInitializer.initialize(context)
        } catch (ex: Exception ) {

        }

        mapView.getMapAsync(this)

        return rootView
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
        })
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
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

    override fun onMapReady(p0: GoogleMap?) {
        searchCountry(p0!!)
    }

    private fun searchCountry(map: GoogleMap) {
        viewLifecycleOwner.lifecycleScope.launch {

            val addresses = withContext(Dispatchers.IO) {
                geocoder.getFromLocationName(countryName, 1)
            }

            if(addresses != null) {
                val address = addresses[0]
                val point = LatLng(address.latitude, address.longitude)

                map!!.addMarker(MarkerOptions().position(point).title(address.featureName).snippet("Description" +
                        "some"))

                val cameraPosition = CameraPosition.Builder().target(point).zoom(5f).build()
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        }
    }
}