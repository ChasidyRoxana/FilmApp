package com.example.filmapp.filmlistscreen.data.model

import com.squareup.moshi.Json

data class FilmApi(
    @Json(name = "genres")
    val genres: List<GenreApi>,
    @Json(name = "original_title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "poster_path")
    val posterUrl: String,
    @Json(name = "video")
    val videoUrl: String,
    @Json(name = "vote_average")
    val rating: Double,
)