package com.valid.businessmodels.business

import com.google.gson.annotations.SerializedName

data class TopArtists(
    @SerializedName("artist")
    val artistList : ArrayList<Artist>
)