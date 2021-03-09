package com.valid.repository.interfaces

import com.valid.businessmodels.request.SignInRequest
import com.valid.businessmodels.response.SignInResponse

interface ISignInRepository {
    suspend fun logIn(signInRequest : SignInRequest): SignInResponse
    suspend fun localLogIn(signInRequest : SignInRequest): SignInResponse
}