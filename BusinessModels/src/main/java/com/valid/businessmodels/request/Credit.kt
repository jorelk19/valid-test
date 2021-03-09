package com.valid.businessmodels.request

data class Credit(
    val code : String = "",
    val type : String = "",
    val groupCode : String = "",
    val installment : Int = 1
)