package com.valid.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.response.PaymentResponse
import com.valid.businessmodels.result.IMyPaymentsResult
import com.valid.domain.MyPaymentsDomain
import com.valid.edson.utils.settingsSharedPreferences
import com.valid.edson.ui.viewModels.base.BaseViewModel

class MyPaymentsViewModel(private val myPaymentsDomain: MyPaymentsDomain) : BaseViewModel() {

    val myPayments = MutableLiveData<ArrayList<PaymentResponse>>()
    fun getMyPayments(): LiveData<ArrayList<PaymentResponse>> {
        return myPayments
    }

    private val myPaymentsResult = object : IMyPaymentsResult {
        override fun setMyPayments(payments: ArrayList<PaymentResponse>) {
            myPayments.value = payments
        }
    }

    fun getMyPaymentsRepository() {
        myPaymentsDomain.errorManager = this
        myPaymentsDomain.domainResult(myPaymentsResult)
        myPaymentsDomain.getLocalPayments(settingsSharedPreferences.getCurrentUser().email)
    }
}