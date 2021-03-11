package com.valid.edson.ui.views.activities.artist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.valid.businessmodels.business.Artist
import com.valid.edson.R
import com.valid.edson.databinding.ActivityArtistDetailBinding
import com.valid.edson.ui.viewModels.ArtistDetailViewModel
import com.valid.edson.ui.views.activities.base.BaseFragmentActivity
import com.valid.edson.utils.getViewModelFactory
import com.valid.edson.utils.loadImage
import com.valid.utils.fromJson

class ArtistDetailActivity : BaseFragmentActivity() {

    private lateinit var binding : ActivityArtistDetailBinding
    private val viewModel by viewModels<ArtistDetailViewModel> { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@ArtistDetailActivity, R.layout.activity_artist_detail)
        binding.viewModel = viewModel
        getExtras()
        addSubscriptions()
    }

    private fun addSubscriptions() {
        viewModel.getImage().observe(this, Observer {
             binding.artistImage.loadImage(it.filter { it.size == "medium" }.first().imageUrl)
        })
    }

    private fun getExtras() {
        intent.extras?.let {
            val artist = it.getString(CURRENT_ARTIST)?.fromJson<Artist>()
            artist?.let {
                viewModel.setCurrentArtist(it)
            }
        }
    }

    companion object{
        const val CURRENT_ARTIST = "CURRENT_ARTIST"
    }

    override fun onBackPressed() {
        this.finish()
    }
}