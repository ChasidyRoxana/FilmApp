package com.example.filmapp.filmlistscreen.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreApi(
    @Json(name = "name")
    val name: String
)