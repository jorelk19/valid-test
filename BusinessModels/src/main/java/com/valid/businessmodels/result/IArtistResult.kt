package com.valid.businessmodels.result

import com.valid.businessmodels.business.TopArtists

interface IArtistResult {
    fun setArtistResult(topArtists: TopArtists)
}