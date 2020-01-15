package com.nazarhuliiev.movieapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nazarhuliiev.movieapp.db.converter.GeneresConverter
import com.nazarhuliiev.movieapp.db.converter.LocalDateConverter
import com.nazarhuliiev.movieapp.db.dao.PopularMoviesDao
import com.nazarhuliiev.movieapp.db.entity.movie.PopularMovieEntity

@Database(entities = [PopularMovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(GeneresConverter::class, LocalDateConverter::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun popularMoviesDao(): PopularMoviesDao
}

private lateinit var INSTANCE: MoviesDatabase

fun getDatabase(context: Context): MoviesDatabase {
    synchronized(MoviesDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "movies_db")
                .build()
        }
    }
    return INSTANCE
}