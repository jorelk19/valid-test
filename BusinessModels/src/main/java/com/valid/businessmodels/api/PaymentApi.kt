package com.valid.businessmodels.api

import com.valid.businessmodels.request.PaymentRequest
import com.valid.businessmodels.response.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApi {
        @POST("/rest/gateway/process")
    suspend fun processTransaction(@Body paymentRequest: PaymentRequest): PaymentResponse
}