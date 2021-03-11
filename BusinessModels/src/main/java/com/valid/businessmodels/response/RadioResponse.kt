package com.valid.businessmodels.response

import com.google.gson.annotations.SerializedName
import com.valid.businessmodels.business.TopArtists

data class  RadioResponse(
    @SerializedName("topartists")
    val topArtists : TopArtists
)