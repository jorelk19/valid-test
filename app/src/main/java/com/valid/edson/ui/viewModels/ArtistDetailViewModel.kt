package com.valid.edson.ui.viewModels

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.business.Artist
import com.valid.businessmodels.business.Image
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.utils.ViewManager

class ArtistDetailViewModel : BaseViewModel() {

    private lateinit var currentArtist: Artist

    val name = MutableLiveData<String>()
    val listeners = MutableLiveData<String>()
    val url = MutableLiveData<String>()
    val streamAble = MutableLiveData<String>()
    val imageList = MutableLiveData<ArrayList<Image>>()

    fun getImage() : LiveData<ArrayList<Image>>{
        return imageList
    }

    fun linkArtistSite() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(currentArtist.url)
        ViewManager.getInstance.getCurrentActivity().startActivity(i)
    }

    fun setCurrentArtist(artist: Artist) {
        currentArtist = artist
        name.value = artist.name
        listeners.value = artist.listeners.toString()
        url.value = artist.url
        streamAble.value = artist.streamAble
        imageList.value = artist.imageList
    }

}