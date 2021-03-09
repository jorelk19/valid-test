package com.valid.repository.interfaces

import com.valid.businessmodels.request.PaymentRequest
import com.valid.businessmodels.response.PaymentResponse

interface IPaymentRepository {
    suspend fun processTransaction(paymentRequest : PaymentRequest) : PaymentResponse
}