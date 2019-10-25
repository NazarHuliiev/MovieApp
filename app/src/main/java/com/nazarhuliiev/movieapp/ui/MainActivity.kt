package com.nazarhuliiev.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nazarhuliiev.movieapp.R
import com.nazarhuliiev.movieapp.ui.MoviesList.MovieListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.contentFrame, MovieListFragment(), "moviesList")
            .commit()
    }
}
