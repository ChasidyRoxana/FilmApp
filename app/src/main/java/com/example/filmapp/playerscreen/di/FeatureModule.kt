package com.example.filmapp.playerscreen.di

import com.example.filmapp.playerscreen.ui.viewModel.PlayerViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {

    factory {
        SimpleExoPlayer.Builder(get()).build()
    }

    viewModel { (videoUri: String) ->
        PlayerViewModel(videoUri)
    }
}