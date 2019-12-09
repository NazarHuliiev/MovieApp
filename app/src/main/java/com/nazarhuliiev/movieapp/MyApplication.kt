package com.nazarhuliiev.movieapp

import android.app.Application
import com.nazarhuliiev.movieapp.di.appModule
import com.nazarhuliiev.movieapp.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(retrofitModule, appModule))
        }
    }
}