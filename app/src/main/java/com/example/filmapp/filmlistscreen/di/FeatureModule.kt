package com.example.filmapp.filmlistscreen.di

import com.example.filmapp.filmlistscreen.data.repository.FilmRepository
import com.example.filmapp.filmlistscreen.data.repository.FilmRepositoryImpl
import com.example.filmapp.filmlistscreen.domain.interactor.FilmInteractor
import com.example.filmapp.filmlistscreen.ui.viewModel.FilmListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmListModule = module {

    single<FilmRepository> {
        FilmRepositoryImpl(get())
    }

    single {
        FilmInteractor(get())
    }

    viewModel {
        FilmListViewModel(get())
    }
}