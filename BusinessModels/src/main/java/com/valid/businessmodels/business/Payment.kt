package com.valid.businessmodels.business

import com.valid.businessmodels.business.Amount

data class Payment(
    val reference :String = "",
    val amount: Amount = Amount()
)