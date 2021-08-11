package com.example.filmapp.filmlistscreen.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.filmapp.R
import com.example.filmapp.base.BaseFragment
import com.example.filmapp.databinding.FragmentFilmListBinding
import com.example.filmapp.filmlistscreen.ui.State
import com.example.filmapp.filmlistscreen.ui.UiEvent
import com.example.filmapp.filmlistscreen.ui.ViewState
import com.example.filmapp.filmlistscreen.ui.filmAdapterDelegate
import com.example.filmapp.filmlistscreen.ui.viewModel.FilmListViewModel
import com.example.filmapp.util.setAdapterAndCleanupOnDetachFromWindow
import com.example.filmapp.util.updateContent
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListFragment : BaseFragment(R.layout.fragment_film_list) {

    private val viewModel: FilmListViewModel by viewModel()
    private val binding by viewBinding(FragmentFilmListBinding::bind)
    private val filmAdapter = ListDelegationAdapter(
        filmAdapterDelegate { viewModel.processUiEvent(UiEvent.OnFilmItemClicked(it)) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupAdapter()
        setupView()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun setupAdapter() {
        binding.rvFilmList.setAdapterAndCleanupOnDetachFromWindow(filmAdapter)
    }

    private fun setupView() {
        binding.srlRefreshFilms.setOnRefreshListener {
            viewModel.processUiEvent(UiEvent.OnScreenRefreshed)
        }
    }

    private fun render(viewState: ViewState) {
        with(binding) {
            srlRefreshFilms.isRefreshing = viewState.isFilmScreenLoading
            layoutError.root.isVisible = viewState.isEmptyErrorVisible
            when (viewState.state) {
                State.LOAD -> Unit
                State.CONTENT -> {
                    filmAdapter.updateContent(viewState.filmList)
                }
                State.ERROR -> {
                    filmAdapter.updateContent(viewState.filmList)
                    Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}