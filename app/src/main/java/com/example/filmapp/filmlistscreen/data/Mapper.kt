package com.example.filmapp.filmlistscreen.data

import com.example.filmapp.filmlistscreen.data.model.FilmListApi
import com.example.filmapp.filmlistscreen.data.model.GenreApi
import com.example.filmapp.filmlistscreen.domain.model.Film
import com.example.filmapp.filmlistscreen.ui.model.FilmUi

fun FilmListApi.mapToFilmList(): List<Film> =
    this.result.map {
        Film(
            genres = it.genres.mapToStringList(),
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterUrl = it.posterUrl,
            videoUrl = it.videoUrl,
            rating = it.rating,
            votersCount = it.votersCount
        )
    }

fun List<GenreApi>.mapToStringList(): List<String> =
    this.map { it.name }

fun List<Film>.mapToFilmUiList(): List<FilmUi> =
    this.map { it.mapToFilmUi() }

fun Film.mapToFilmUi() =
    FilmUi(
        title = this.title,
        posterUrl = this.posterUrl,
        rating = this.rating.toString()
    )