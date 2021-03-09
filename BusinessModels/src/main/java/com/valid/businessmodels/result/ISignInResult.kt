package com.valid.businessmodels.result

import com.valid.businessmodels.response.SignInResponse

interface ISignInResult {
    fun logIn(signInResponse: SignInResponse)
}