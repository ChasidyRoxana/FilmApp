package com.example.filmapp.playerscreen.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.filmapp.R
import com.example.filmapp.base.BaseFragment
import com.example.filmapp.databinding.FragmentPlayerBinding
import com.example.filmapp.playerscreen.ui.State
import com.example.filmapp.playerscreen.ui.ViewState
import com.example.filmapp.playerscreen.ui.viewModel.PlayerViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PlayerFragment : BaseFragment(R.layout.fragment_player) {

    companion object {

        private const val VIDEO_URI = "VIDEO_URI"

        fun newInstance(videoUri: String) =
            PlayerFragment().apply {
                arguments = bundleOf(VIDEO_URI to videoUri)
            }
    }

    private val binding by viewBinding(FragmentPlayerBinding::bind)
    private val viewModel: PlayerViewModel by viewModel {
        parametersOf(
            requireArguments().getString(VIDEO_URI)
        )
    }
    private val player: SimpleExoPlayer by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        initPlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        when (viewState.state) {
            State.LOAD -> Unit
            State.CONTENT -> {
                viewState.videoUri?.let {
                    val mediaItem = MediaItem.fromUri(it)
                    player.apply {
                        setMediaItem(mediaItem)
                        prepare()
                        play()
                    }
                }
            }
            State.ERROR -> Unit
        }
    }

    private fun initPlayer() {
        binding.pvPlayer.player = player
    }

    private fun releasePlayer() {
        player.release()
        binding.pvPlayer.player = null
    }
}