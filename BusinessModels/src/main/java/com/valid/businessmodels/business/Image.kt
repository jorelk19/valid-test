package com.valid.businessmodels.business

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val imageUrl : String = "",
    val size : String = ""
)