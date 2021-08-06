package com.example.filmapp.filmlistscreen.domain.interactor

import com.example.filmapp.filmlistscreen.data.mapToFilmUiList
import com.example.filmapp.filmlistscreen.data.repository.FilmRepository
import com.example.filmapp.filmlistscreen.ui.model.FilmUi

class FilmInteractor(private val filmRepository: FilmRepository) {

    suspend fun getFilms(): Result<List<FilmUi>> {
        return runCatching {
            filmRepository.getFilms().mapToFilmUiList()
        }
    }
}