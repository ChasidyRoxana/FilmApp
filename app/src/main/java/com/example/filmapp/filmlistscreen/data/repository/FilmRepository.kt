package com.example.filmapp.filmlistscreen.data.repository

import com.example.filmapp.filmlistscreen.domain.model.Film

interface FilmRepository {
    suspend fun getFilms(): List<Film>
}