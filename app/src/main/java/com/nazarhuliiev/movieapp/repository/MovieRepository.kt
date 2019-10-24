package com.nazarhuliiev.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieRepository {

    fun getMovie() : MutableLiveData<LocalMovie>{

        return MutableLiveData(LocalMovie("El Camino", 2019, 7.6f));
    }
}