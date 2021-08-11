package com.example.filmapp.filmlistscreen.ui.model

import com.example.filmapp.base.Item

data class FilmItem(
    val title: String,
    val overview: String,
    val genres: String,
    val posterUrl: String,
    val videoUrl: String,
    val rating: String,
    val releaseDate: String,
) : Item {
    val genresQuantity = genres.count { it == ',' } + 1
}