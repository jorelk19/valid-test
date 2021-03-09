package com.valid.businessmodels.response

import com.valid.businessmodels.business.User

data class SignInResponse(
    val isValid: Boolean = false,
    val user: User = User()
)