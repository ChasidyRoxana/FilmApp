package com.example.filmapp.filmlistscreen.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.filmapp.R
import com.example.filmapp.base.BaseFragment
import com.example.filmapp.databinding.FragmentFilmListBinding
import com.example.filmapp.filmlistscreen.FilmListViewState
import com.example.filmapp.filmlistscreen.STATUS
import com.example.filmapp.filmlistscreen.UiEvent
import com.example.filmapp.filmlistscreen.ui.filmAdapterDelegate
import com.example.filmapp.filmlistscreen.ui.model.FilmUi
import com.example.filmapp.filmlistscreen.ui.model.Item
import com.example.filmapp.filmlistscreen.ui.viewModel.FilmListViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListFragment : BaseFragment<FilmListViewState, FilmListViewModel>(
    R.layout.fragment_film_list
) {

    override val viewModel: FilmListViewModel by viewModel()
    private val binding by viewBinding(FragmentFilmListBinding::bind)
    private lateinit var filmAdapter: ListDelegationAdapter<List<Item>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupView()
    }

    private fun setupAdapter() {
        filmAdapter = ListDelegationAdapter(
            filmAdapterDelegate()
        )
        binding.rvFilmList.adapter = filmAdapter
    }

    private fun setupView() {
        binding.srlRefreshFilms.setOnRefreshListener {
            viewModel.processUiEvent(UiEvent.OnRefreshFilms)
        }
    }

    override fun render(viewState: FilmListViewState) {
        with(binding) {
            when (viewState.status) {
                STATUS.LOAD -> {
                    srlRefreshFilms.isRefreshing = true
                }
                STATUS.CONTENT -> {
                    srlRefreshFilms.isRefreshing = false
                    updateContent(viewState.filmList)
                }
                STATUS.ERROR -> {
                    srlRefreshFilms.isRefreshing = false
                    updateContent(viewState.filmList)
                    Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_LONG)
                        .show()
                }
            }
            layoutError.root.isVisible = viewState.isEmptyErrorVisible
        }
    }

    private fun updateContent(filmList: List<FilmUi>) {
        filmAdapter.items = filmList
        filmAdapter.notifyItemRangeChanged(0, filmList.size)
    }
}