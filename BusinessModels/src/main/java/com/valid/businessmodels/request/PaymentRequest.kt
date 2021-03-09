package com.valid.businessmodels.request

import com.valid.businessmodels.business.Person
import com.valid.businessmodels.business.Payment
import com.valid.businessmodels.request.Auth
import com.valid.businessmodels.request.Instrument

data class PaymentRequest(
    val locale : String = "es_CO",
    val auth : Auth = Auth(),
    val instrument: Instrument = Instrument(),
    val payment : Payment = Payment(),
    val payer : Person = Person(),
    val buyer : Person = Person(),
    val ipAddress : String = "",
    val userAgent : String = ""
)