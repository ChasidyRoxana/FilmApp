package com.example.filmapp.filmlistscreen.data

import com.example.filmapp.filmlistscreen.data.model.FilmListApi
import com.example.filmapp.filmlistscreen.data.model.GenreApi
import com.example.filmapp.filmlistscreen.domain.model.Film
import com.example.filmapp.filmlistscreen.domain.model.Genre

fun FilmListApi.mapToFilmList(): List<Film> =
    this.result.map {
        Film(
            genres = it.genres.mapToGenreList(),
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterUrl = it.posterUrl,
            videoUrl = it.videoUrl,
            rating = it.rating,
        )
    }

fun List<GenreApi>.mapToGenreList(): List<Genre> =
    this.map { it.mapToGenre() }

fun GenreApi.mapToGenre() =
    Genre(
        name = this.name
    )