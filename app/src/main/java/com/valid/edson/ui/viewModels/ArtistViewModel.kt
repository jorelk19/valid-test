package com.valid.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.business.TopArtists
import com.valid.businessmodels.result.IArtistResult
import com.valid.domain.ArtistDomain
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.edson.utils.settingsSharedPreferences

class ArtistViewModel(private val artistDomain: ArtistDomain) : BaseViewModel() {
    val searchedText = MutableLiveData<String>()
    val currentTopArtists = MutableLiveData<TopArtists>()

    fun getTopArtist() : LiveData<TopArtists>{
        return currentTopArtists
    }

    private val trackResult = object : IArtistResult {
        override fun setArtistResult(topArtists: TopArtists) {
            currentTopArtists.value = topArtists
        }
    }

    fun initControls() {
        artistDomain.errorManager = this
        artistDomain.domainResult(trackResult)
        artistDomain.searchArtist(settingsSharedPreferences.getCountry())
    }

    fun searchButtonClick(){
        searchedText.value?.let {
            if(it.length > 3){
                artistDomain.searchArtist(settingsSharedPreferences.getCountry())
            }
        }
    }
}