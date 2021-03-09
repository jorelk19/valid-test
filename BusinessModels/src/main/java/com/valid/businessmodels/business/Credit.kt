package com.valid.businessmodels.business

data class Credit(
    var code: Int = 0,
    var type: String? = null,
    var groupCode: String? = null,
    var installments: List<Int>? = null,
    var installment: Int = 0,
    var description: String? = null
)