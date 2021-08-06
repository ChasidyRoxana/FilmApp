package com.example.filmapp.filmlistscreen.domain.model

data class Film(
    val genres: List<String>,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterUrl: String,
    val videoUrl: String,
    val rating: Double,
    val votersCount: Int
)