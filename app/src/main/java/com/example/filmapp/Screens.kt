package com.example.filmapp

import com.example.filmapp.filmdetailscreen.ui.fragment.FilmDetailFragment
import com.example.filmapp.filmlistscreen.ui.fragment.FilmListFragment
import com.example.filmapp.filmlistscreen.ui.model.FilmItem
import com.example.filmapp.playerscreen.ui.fragment.PlayerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun filmListScreen() =
        FragmentScreen { FilmListFragment.newInstance() }

    fun filmDetailScreen(filmItem: FilmItem) =
        FragmentScreen { FilmDetailFragment.newInstance(filmItem) }

    fun playerScreen(videoUri: String) =
        FragmentScreen { PlayerFragment.newInstance(videoUri) }
}