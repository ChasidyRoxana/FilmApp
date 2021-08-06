package com.example.filmapp

import com.example.filmapp.filmlistscreen.data.network.FilmApiService
import com.example.filmapp.filmlistscreen.data.network.NetworkApi
import org.koin.dsl.module

val networkModule = module {

    single<FilmApiService> {
        NetworkApi().createFilmApiService()
    }
}