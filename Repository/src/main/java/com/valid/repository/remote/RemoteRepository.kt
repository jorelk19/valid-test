package com.valid.repository.remote

import com.valid.businessmodels.api.RadioApi
import com.valid.businessmodels.response.RadioResponse
import com.valid.repository.BuildConfig

/**
 * Class used to load remote repository information
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class RemoteRepository(private val radioApi: RadioApi) {
    suspend fun getTracks(countryName : String): RadioResponse {
        return radioApi.getGeoTracks(
            BuildConfig.METHOD,
            countryName
        )
    }
}