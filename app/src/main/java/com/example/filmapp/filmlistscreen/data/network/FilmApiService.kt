package com.example.filmapp.filmlistscreen.data.network

import com.example.filmapp.filmlistscreen.data.model.FilmListApi
import retrofit2.http.GET

interface FilmApiService {

    @GET("movies.json")
    suspend fun getFilms(): FilmListApi
}