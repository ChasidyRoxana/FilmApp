package com.example.filmapp

import android.app.Application
import com.example.filmapp.filmlistscreen.di.filmListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                networkModule,
                filmListModule,
            )
        }
    }
}