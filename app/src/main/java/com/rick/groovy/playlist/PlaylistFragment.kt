package com.rick.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rick.groovy.R

/**
 * A fragment representing a list of Items.
 */
class PlaylistFragment : Fragment() {

    private lateinit var viewModel: PlaylistViewModel
    private lateinit var viewModelFactory: PlaylistViewModelFactory
    private lateinit var repository: PlaylistRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        repository = PlaylistRepository()
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)

        // Set the adapter

        viewModel.playlists.observe(viewLifecycleOwner,
            {playtlist ->
                if (playtlist.getOrNull() != null) {
                    with(view as RecyclerView) {
                        layoutManager = LinearLayoutManager(context)

                        adapter = PlaylistRecyclerViewAdapter(playtlist.getOrNull()!!)
                    }
                } else {
                    //TODO
                }

            }
        )
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}