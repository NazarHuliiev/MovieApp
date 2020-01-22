package com.nazarhuliiev.movieapp.helpers

import com.nazarhuliiev.movieapp.BuildConfig

object UrlHelper {
    fun getImagePath(imageName: String): String {
        return BuildConfig.IMAGE_SERVER_URL + imageName
    }
}