package com.example.filmapp.filmlistscreen.data.network

import com.example.filmapp.filmlistscreen.data.model.FilmListApi
import retrofit2.http.GET

interface FilmApiService {

    @GET("LukyanovAnatoliy/eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/movies.json")
    suspend fun getFilms(): FilmListApi
}