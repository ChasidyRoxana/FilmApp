package com.example.filmapp.filmlistscreen.data.model

import com.google.gson.annotations.SerializedName

data class FilmListApi(
    @SerializedName("results")
    val result: List<FilmApi>
)