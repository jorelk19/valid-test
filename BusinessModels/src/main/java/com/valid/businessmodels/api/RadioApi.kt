package com.valid.businessmodels.api

import com.valid.businessmodels.response.RadioResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RadioApi {
    @GET("/2.0/")
    suspend fun getGeoTracks(
        @Query("method") method: String,
        @Query("country") country: String): RadioResponse

    @POST("/2.0/")
    suspend fun getGeoTopArtist(): RadioResponse
}