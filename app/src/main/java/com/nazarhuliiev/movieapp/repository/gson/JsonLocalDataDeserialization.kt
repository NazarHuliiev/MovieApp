package com.nazarhuliiev.movieapp.repository.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.LocalDate
import java.lang.reflect.Type
import com.google.gson.JsonParseException
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException


class JsonLocalDataDeserialization: JsonDeserializer<LocalDate> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        try {
            if (typeOfT === LocalDate::class.java) {
                if (json != null) {
                    return LocalDate.parse(
                        json.asJsonPrimitive.asString,
                        DateTimeFormatter.ISO_DATE
                    )
                }
                return  LocalDate.MIN
            }
        } catch (e: DateTimeParseException) {
            throw JsonParseException(e)
        }

        throw IllegalArgumentException("unknown type: $typeOfT")
    }
}