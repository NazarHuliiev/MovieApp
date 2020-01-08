package com.nazarhuliiev.movieapp.db.converter

import androidx.room.TypeConverter

class GeneresConverter {

    @TypeConverter
    fun toString(genres: List<Int>): String {
        return genres.joinToString("")
    }

    @TypeConverter
    fun toList(genres: String): List<Int> {
        return  genres.map { it.toString().toInt() }.toList()
    }
}