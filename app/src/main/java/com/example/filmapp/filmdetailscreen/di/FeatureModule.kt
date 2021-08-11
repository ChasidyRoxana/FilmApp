package com.example.filmapp.filmdetailscreen.di

import com.example.filmapp.filmdetailscreen.ui.viewModel.FilmDetailViewModel
import com.example.filmapp.filmlistscreen.ui.model.FilmItem
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmDetailModule = module {

    viewModel { (filmItem: FilmItem) ->
        FilmDetailViewModel(get(), filmItem)
    }
}