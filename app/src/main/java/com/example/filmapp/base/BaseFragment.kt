package com.example.filmapp.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<
        ViewState : BaseViewState,
        ViewModel : BaseViewModel<ViewState>>(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes) {

    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    protected abstract fun render(viewState: ViewState)

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }
}