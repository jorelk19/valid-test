package com.valid.businessmodels.result

import com.valid.businessmodels.response.PaymentResponse

interface IMyPaymentsResult {
    fun setMyPayments(payments : ArrayList<PaymentResponse>)
}