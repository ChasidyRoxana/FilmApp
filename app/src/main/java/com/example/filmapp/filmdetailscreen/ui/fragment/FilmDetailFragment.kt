package com.example.filmapp.filmdetailscreen.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.filmapp.R
import com.example.filmapp.base.BaseFragment
import com.example.filmapp.databinding.FragmentFilmDetailBinding
import com.example.filmapp.filmdetailscreen.ui.State
import com.example.filmapp.filmdetailscreen.ui.UiEvent
import com.example.filmapp.filmdetailscreen.ui.ViewState
import com.example.filmapp.filmdetailscreen.ui.viewModel.FilmDetailViewModel
import com.example.filmapp.filmlistscreen.ui.model.FilmItem
import com.example.filmapp.util.fromHtml
import com.example.filmapp.util.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FilmDetailFragment : BaseFragment(R.layout.fragment_film_detail) {

    companion object {

        private const val TITLE = "TITLE"
        private const val OVERVIEW = "OVERVIEW"
        private const val GENRES = "GENRES"
        private const val POSTER_URI = "POSTER_URI"
        private const val VIDEO_URI = "VIDEO_URI"
        private const val RATING = "RATING"
        private const val RELEASE_DATE = "RELEASE_DATE"

        fun newInstance(filmItem: FilmItem) = FilmDetailFragment().apply {
            arguments = bundleOf(
                TITLE to filmItem.title,
                OVERVIEW to filmItem.overview,
                GENRES to filmItem.genres,
                POSTER_URI to filmItem.posterUrl,
                VIDEO_URI to filmItem.videoUrl,
                RATING to filmItem.rating,
                RELEASE_DATE to filmItem.releaseDate
            )
        }
    }

    private val binding by viewBinding(FragmentFilmDetailBinding::bind)
    private val viewModel: FilmDetailViewModel by viewModel {
        parametersOf(
            with(requireArguments()) {
                FilmItem(
                    title = getString(TITLE, ""),
                    overview = getString(OVERVIEW, ""),
                    genres = getString(GENRES, ""),
                    posterUrl = getString(POSTER_URI, ""),
                    videoUrl = getString(VIDEO_URI, ""),
                    rating = getString(RATING, ""),
                    releaseDate = getString(RELEASE_DATE, "")
                )
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupView()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun setupView() {
        with(binding) {
            tbAppBar.setNavigationOnClickListener {
                viewModel.processUiEvent(UiEvent.OnNavigationBackClicked)
            }
            bWatch.setOnClickListener {
                viewModel.processUiEvent(UiEvent.OnWatchButtonClicked)
            }
        }
    }

    private fun render(viewState: ViewState) {
        when (viewState.state) {
            State.LOAD -> Unit
            State.CONTENT -> {
                viewState.filmItem?.let {
                    updateContent(it)
                }
            }
            State.ERROR -> Unit
        }
    }

    private fun updateContent(filmItem: FilmItem) {
        with(binding) {
            ivPoster.loadImage(filmItem.posterUrl)
            with(layoutFilmDetail) {
                tvFilmName.text = filmItem.title
                tvRating.text = getString(R.string.text_rating, filmItem.rating).fromHtml()
                val genresTitle =
                    resources.getQuantityString(R.plurals.plurals_genre, filmItem.genresQuantity)
                tvGenres.text =
                    getString(R.string.text_genres, genresTitle, filmItem.genres).fromHtml()
                tvReleaseDate.text =
                    getString(R.string.text_release_date, filmItem.releaseDate).fromHtml()
                tvOverview.text = filmItem.overview
            }
        }
    }
}