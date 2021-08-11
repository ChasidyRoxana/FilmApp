package com.example.filmapp

import com.example.filmapp.filmdetailscreen.ui.fragment.FilmDetailFragment
import com.example.filmapp.filmlistscreen.ui.fragment.FilmListFragment
import com.example.filmapp.filmlistscreen.ui.model.FilmItem
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun filmListScreen() =
        FragmentScreen { FilmListFragment() }

    fun filmDetailScreen(filmItem: FilmItem) =
        FragmentScreen { FilmDetailFragment(filmItem) }
}