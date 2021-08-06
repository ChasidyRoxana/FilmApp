package com.example.filmapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.filmapp.R

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}