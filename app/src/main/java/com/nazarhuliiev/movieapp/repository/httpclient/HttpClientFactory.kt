package com.nazarhuliiev.movieapp.repository.httpclient

import okhttp3.OkHttpClient

class HttpClientFactory {
    companion object{
        fun create(): OkHttpClient{
            val client = OkHttpClient.Builder()
            client.addInterceptor(ApiKeyInterceptor())

            return client.build()
        }
    }
}