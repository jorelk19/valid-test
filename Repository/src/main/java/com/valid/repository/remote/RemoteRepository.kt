package com.valid.repository.remote

import com.valid.businessmodels.api.SignInApi
import com.valid.businessmodels.response.SignInResponse

/**
 * Class used to load remote movie information
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class RemoteRepository(private val signInApi: SignInApi) {
    suspend fun getRemoteMovies(): SignInResponse {
        return signInApi.signIn()
    }
}