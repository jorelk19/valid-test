package com.valid.edson.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.valid.edson.R
import com.valid.edson.databinding.LayoutTrackFragmentBinding
import com.valid.edson.ui.viewModels.TrackViewModel
import com.valid.edson.ui.views.fragments.adapters.ArtistAdapter
import com.valid.edson.utils.getViewModelFactory
import com.valid.utils.ViewManager

class TrackFragment : Fragment() {

    private lateinit var trackFragmentBinding: LayoutTrackFragmentBinding
    private lateinit var artistAdapter: ArtistAdapter
    val viewModel by viewModels<TrackViewModel> { getViewModelFactory() }

    /**
     * Method to instantiate the view
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        trackFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.layout_track_fragment, container, false)
        trackFragmentBinding.lifecycleOwner = ViewManager.getInstance.getCurrentActivity()
        trackFragmentBinding.viewModel = viewModel
        viewModel.initControls()
        addSubscriptions()
        setAdapters()
        return trackFragmentBinding.root
    }

    private fun addSubscriptions() {
        viewModel.getTopArtist().observe(viewLifecycleOwner, Observer {
            artistAdapter.addItems(it.artistList)
        })
    }

    private fun setAdapters() {
        artistAdapter = ArtistAdapter(ViewManager.getInstance.getCurrentActivity(), arrayListOf())
        trackFragmentBinding.rvArtists.layoutManager = LinearLayoutManager(ViewManager.getInstance.getCurrentActivity())
        trackFragmentBinding.rvArtists.adapter = artistAdapter
    }
}