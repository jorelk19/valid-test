package com.valid.edson.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.valid.edson.R
import com.valid.edson.databinding.LayoutArtistFragmentBinding
import com.valid.edson.ui.viewModels.ArtistViewModel
import com.valid.edson.ui.views.fragments.adapters.ArtistAdapter
import com.valid.edson.utils.getViewModelFactory
import com.valid.utils.ViewManager

class ArtistFragment : Fragment() {

    private lateinit var artistFragmentBinding: LayoutArtistFragmentBinding
    private lateinit var artistAdapter: ArtistAdapter
    val viewModel by viewModels<ArtistViewModel> { getViewModelFactory() }

    /**
     * Method to instantiate the view
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        artistFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.layout_artist_fragment, container, false)
        artistFragmentBinding.lifecycleOwner = ViewManager.getInstance.getCurrentActivity()
        artistFragmentBinding.viewModel = viewModel
        artistFragmentBinding.lifecycleOwner = this
        addSubscriptions()
        setAdapters()
        return artistFragmentBinding.root
    }

    private fun addSubscriptions() {
        viewModel.getTopArtist().observe(viewLifecycleOwner, Observer {
            artistAdapter.addItems(it.artistList)
        })
    }

    private fun setAdapters() {
        artistAdapter = ArtistAdapter(ViewManager.getInstance.getCurrentActivity(), arrayListOf())
        artistFragmentBinding.rvArtists.layoutManager = LinearLayoutManager(ViewManager.getInstance.getCurrentActivity())
        artistFragmentBinding.rvArtists.adapter = artistAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.initControls()
    }
}