package com.example.filmapp.filmlistscreen.data.model

import com.squareup.moshi.Json

data class GenreApi(
    @Json(name = "name")
    val name: String
)