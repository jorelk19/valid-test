package com.valid.businessmodels.business

data class Person(
    val documentType: String = "",
    val document: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = ""
)