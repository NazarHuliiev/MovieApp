package com.nazarhuliiev.movieapp.service.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.threeten.bp.LocalDate

class GsonFactory {
    companion object{
        fun create():Gson{
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(LocalDate::class.java, JsonLocalDataDeserialization())

            return gsonBuilder.create()
        }
    }
}