package com.valid.businessmodels.request

import com.valid.businessmodels.request.Card
import com.valid.businessmodels.request.Credit

data class Instrument(
    val card: Card = Card(),
    val token: String = "",
    val credit: Credit = Credit(),
    val otp: String = ""
)