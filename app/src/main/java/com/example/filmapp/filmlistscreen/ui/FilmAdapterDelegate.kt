package com.example.filmapp.filmlistscreen.ui

import com.example.filmapp.databinding.ItemFilmCardBinding
import com.example.filmapp.filmlistscreen.ui.model.FilmUi
import com.example.filmapp.filmlistscreen.ui.model.Item
import com.example.filmapp.util.loadImage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun filmAdapterDelegate() =
    adapterDelegateViewBinding<FilmUi, Item, ItemFilmCardBinding>(
        { layoutInflater, parent -> ItemFilmCardBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            with(binding) {
                tvFilmName.text = item.title
                tvRating.text = item.rating
                ivPoster.loadImage(item.posterUrl)
            }
        }
    }