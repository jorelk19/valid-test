package com.valid.businessmodels.business

data class User(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val cellPhoneNumber: String = ""
)