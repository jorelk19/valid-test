package com.valid.repository.implementation

import com.valid.businessmodels.api.PaymentApi
import com.valid.businessmodels.request.PaymentRequest
import com.valid.businessmodels.response.PaymentResponse
import com.valid.repository.interfaces.IPaymentRepository

class PaymentRepository(private val paymentApi: PaymentApi) : IPaymentRepository {
    override suspend fun processTransaction(paymentRequest: PaymentRequest): PaymentResponse {
        return paymentApi.processTransaction(paymentRequest)
    }
}