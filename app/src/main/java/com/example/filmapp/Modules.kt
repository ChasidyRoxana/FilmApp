package com.example.filmapp

import com.example.filmapp.filmlistscreen.data.network.FilmApiService
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://gist.githubusercontent.com/"
private const val OKHTTP_TIMEOUT: Long = 20

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<FilmApiService> {
        get<Retrofit>().create(FilmApiService::class.java)
    }
}

val ciceroneModule = module {

    single<Cicerone<Router>> {
        Cicerone.create()
    }

    single<Router> {
        get<Cicerone<Router>>().router
    }

    single<NavigatorHolder> {
        get<Cicerone<Router>>().getNavigatorHolder()
    }
}