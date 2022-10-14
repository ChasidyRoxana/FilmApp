package com.example.filmapp.filmlistscreen.data.model

import com.squareup.moshi.Json

data class FilmListApi(
    @Json(name = "results")
    val result: List<FilmApi>
)