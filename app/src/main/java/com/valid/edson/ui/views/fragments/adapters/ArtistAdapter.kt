package com.valid.edson.ui.views.fragments.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.valid.businessmodels.business.Artist
import com.valid.edson.R
import com.valid.edson.databinding.LayoutArtistItemBinding
import com.valid.edson.utils.loadImage
import com.valid.utils.GenericAdapter
import com.valid.utils.ViewManager


class ArtistAdapter(context: Context, artists: ArrayList<Artist>) : GenericAdapter<Artist, LayoutArtistItemBinding>(context, artists) {
    override fun getLayoutResId(): Int {
        return R.layout.layout_artist_item
    }

    override fun onBindData(model: Artist, position: Int, dataBinding: LayoutArtistItemBinding) {
        dataBinding.ivArtist.loadImage(model.imageList.first().imageUrl)
        dataBinding.tvArtistName.text = model.name
        dataBinding.tvListeners.text = model.listeners.toString()
        dataBinding.tvUrl.text = model.url
        setListeners(model, dataBinding)
    }

    private fun setListeners(model: Artist, dataBinding: LayoutArtistItemBinding) {
        dataBinding.tvUrl.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(model.url)
            ViewManager.getInstance.getCurrentActivity().startActivity(i)
        }
    }
}