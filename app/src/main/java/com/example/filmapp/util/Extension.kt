package com.example.filmapp.util

import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmapp.R
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}

fun RecyclerView.setAdapterAndCleanupOnDetachFromWindow(recyclerViewAdapter: RecyclerView.Adapter<*>) {
    adapter = recyclerViewAdapter
    val lifecycle = findViewTreeLifecycleOwner()?.lifecycle
    lifecycle?.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            adapter = null
            owner.lifecycle.removeObserver(this)
        }
    })
}

fun <T> AbsDelegationAdapter<T>.updateContent(content: T) {
    items = content
    notifyDataSetChanged()
}

fun String.fromHtml() =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)