package com.valid.businessmodels.business

import com.valid.businessmodels.business.Amount

data class Conversion(
    var from: Amount = Amount(),
    var to: Amount = Amount(),
    var factor: Double = 0.0
)