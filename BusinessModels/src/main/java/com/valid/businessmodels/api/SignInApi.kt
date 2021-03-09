package com.valid.businessmodels.api

import com.valid.businessmodels.response.SignInResponse
import retrofit2.http.GET

/**
 * Interface used to get the movies from the given api
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
interface SignInApi {
    @GET("/4/list/1")
    suspend fun signIn(): SignInResponse
}