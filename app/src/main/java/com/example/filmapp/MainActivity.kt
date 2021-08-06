package com.example.filmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmapp.filmlistscreen.ui.fragment.FilmListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.flContainer, FilmListFragment())
                .commit()
        }
    }
}