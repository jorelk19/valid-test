package com.valid.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.business.TopArtists
import com.valid.businessmodels.result.ITrackResult
import com.valid.domain.TrackDomain
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.edson.utils.settingsSharedPreferences

class TrackViewModel(private val trackDomain: TrackDomain) : BaseViewModel() {
    val searchedText = MutableLiveData<String>()
    val currentTopArtists = MutableLiveData<TopArtists>()

    fun getTopArtist() : LiveData<TopArtists>{
        return currentTopArtists
    }

    private val trackResult = object : ITrackResult {
        override fun setTrackResult(topArtists: TopArtists) {
            currentTopArtists.value = topArtists
        }
    }

    fun initControls() {
        trackDomain.errorManager = this
        trackDomain.domainResult(trackResult)
    }

    fun searchButtonClick(){
        searchedText.value?.let {
            if(it.length > 3){
                trackDomain.searchTrack(it, settingsSharedPreferences.getCountry())
            }
        }
    }
}