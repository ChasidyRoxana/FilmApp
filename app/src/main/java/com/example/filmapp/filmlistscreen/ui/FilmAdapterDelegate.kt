package com.example.filmapp.filmlistscreen.ui

import com.example.filmapp.base.Item
import com.example.filmapp.databinding.ItemFilmCardBinding
import com.example.filmapp.filmlistscreen.ui.model.FilmItem
import com.example.filmapp.util.loadImage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun filmAdapterDelegate(onFilmItemClicked: (FilmItem) -> Unit) =
    adapterDelegateViewBinding<FilmItem, Item, ItemFilmCardBinding>(
        { layoutInflater, parent -> ItemFilmCardBinding.inflate(layoutInflater, parent, false) }
    ) {
        with(binding) {

            clContainer.setOnClickListener { onFilmItemClicked(item) }

            bind {
                tvFilmName.text = item.title
                tvRating.text = item.rating
                ivPoster.loadImage(item.posterUrl)
            }
        }
    }