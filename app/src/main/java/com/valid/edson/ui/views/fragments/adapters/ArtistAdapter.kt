package com.valid.edson.ui.views.fragments.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.valid.businessmodels.business.Artist
import com.valid.edson.R
import com.valid.edson.databinding.LayoutArtistItemBinding
import com.valid.edson.ui.views.activities.artist.ArtistDetailActivity
import com.valid.edson.utils.loadImage
import com.valid.utils.GenericAdapter
import com.valid.utils.ViewManager
import com.valid.utils.json


class ArtistAdapter(context: Context, artists: ArrayList<Artist>) : GenericAdapter<Artist, LayoutArtistItemBinding>(context, artists) {
    override fun getLayoutResId(): Int {
        return R.layout.layout_artist_item
    }

    override fun onBindData(model: Artist, position: Int, dataBinding: LayoutArtistItemBinding) {
        dataBinding.ivArtist.loadImage(model.imageList.first().imageUrl)
        dataBinding.tvArtistName.text = model.name
        setListeners(model, dataBinding)
    }

    private fun setListeners(model: Artist, dataBinding: LayoutArtistItemBinding) {
        dataBinding.artistContainer.setOnClickListener {
            ViewManager.getInstance.goTo(ArtistDetailActivity::class.java, Bundle().apply {
                putString(ArtistDetailActivity.CURRENT_ARTIST, model.json())
            })
        }
    }
}