package com.valid.businessmodels.result

import com.valid.businessmodels.response.PaymentResponse

interface IPaymentResult {
    fun setInformationRequest(paymentResponse: PaymentResponse)
}