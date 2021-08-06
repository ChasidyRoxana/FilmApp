package com.example.filmapp.filmlistscreen.data.repository

import com.example.filmapp.filmlistscreen.data.mapToFilmList
import com.example.filmapp.filmlistscreen.data.network.FilmApiService
import com.example.filmapp.filmlistscreen.domain.model.Film

class FilmRepositoryImpl(private val api: FilmApiService) : FilmRepository {

    override suspend fun getFilms(): List<Film> =
        api.getFilms().mapToFilmList()
}