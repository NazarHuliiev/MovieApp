package com.nazarhuliiev.movieapp.db.converter

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class LocalDateConverter {

    @TypeConverter
    fun toLocalDate(dateString: String): LocalDate {
        return LocalDate.parse(
            dateString,
            DateTimeFormatter.ISO_DATE
        )
    }

    @TypeConverter
    fun toString(date: LocalDate): String {
        return date.toString()
    }
}