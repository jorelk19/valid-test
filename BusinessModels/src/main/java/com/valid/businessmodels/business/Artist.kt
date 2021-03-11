package com.valid.businessmodels.business

import com.google.gson.annotations.SerializedName

data class Artist(
    val name : String = "",
    val listeners : Long = 0,
    @SerializedName("mbid")
    val mbId : String = "",
    val url : String = "",
    @SerializedName("streamable")
    val streamAble : String = "",
    @SerializedName("image")
    val imageList : ArrayList<Image> = arrayListOf()
)