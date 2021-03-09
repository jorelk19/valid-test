package com.valid.businessmodels.request

data class Card(
    val number: String = "",
    val expirationMonth : String = "",
    val expirationYear : String = "",
    val cvv : String = ""
)