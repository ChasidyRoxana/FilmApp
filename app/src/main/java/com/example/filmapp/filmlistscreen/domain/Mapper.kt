package com.example.filmapp.filmlistscreen.domain

import com.example.filmapp.filmlistscreen.domain.model.Film
import com.example.filmapp.filmlistscreen.domain.model.Genre
import com.example.filmapp.filmlistscreen.ui.model.FilmItem

fun List<Film>.mapToFilmItemList(): List<FilmItem> =
    this.map { it.mapToFilmUi() }

fun Film.mapToFilmUi() =
    FilmItem(
        title = title,
        overview = overview,
        genres = genres.mapToString(),
        posterUrl = posterUrl,
        videoUrl = videoUrl,
        rating = rating.toString(),
        releaseDate = releaseDate
    )

fun List<Genre>.mapToString(): String {
    val genres: List<String> = this.map {
        it.mapToString()
    }
    return genres.joinToString()
}

fun Genre.mapToString() =
    name