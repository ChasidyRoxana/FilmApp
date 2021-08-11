package com.example.filmapp.filmlistscreen.domain.interactor

import com.example.filmapp.filmlistscreen.data.repository.FilmRepository
import com.example.filmapp.filmlistscreen.domain.mapToFilmItemList
import com.example.filmapp.filmlistscreen.ui.model.FilmItem

class FilmInteractor(private val filmRepository: FilmRepository) {

    suspend fun getFilms(): Result<List<FilmItem>> {
        return runCatching {
            filmRepository.getFilms().mapToFilmItemList()
        }
    }
}