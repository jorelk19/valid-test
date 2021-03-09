package com.valid.edson.ui.viewModels

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.business.Payment
import com.valid.businessmodels.business.Person
import com.valid.businessmodels.business.Amount
import com.valid.businessmodels.request.Card
import com.valid.businessmodels.response.PaymentResponse
import com.valid.businessmodels.result.IPaymentResult
import com.valid.domain.PaymentDomain
import com.valid.edson.R
import com.valid.edson.utils.NetworkUtil
import com.valid.edson.utils.settingsSharedPreferences
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.edson.ui.views.activities.NotificationActivity
import com.valid.utils.ViewManager
import com.valid.utils.json

class PaymentViewModel(private val paymentDomain: PaymentDomain) : BaseViewModel() {

    /*Payment Information*/
    var cardNumber = MutableLiveData<String>()
    var cvvNumber = MutableLiveData<String>()
    var expirationDate = MutableLiveData<String>()
    var cardName = MutableLiveData<String>()
    var totalPayment = MutableLiveData<String>()
    var buttonPaymentEnabled = MutableLiveData<Boolean>()

    fun getCardNumber(): LiveData<String> = cardNumber
    fun getCvvNumber(): LiveData<String> = cvvNumber
    fun getExpirationDate(): LiveData<String> = expirationDate
    fun getCardName(): LiveData<String> = cardName
    fun getTotalPayment(): LiveData<String> = totalPayment

    /*Visual Information*/
    val cardNumberDescription = MutableLiveData<String>()
    val cvvNumberDescription = MutableLiveData<String>()
    val expirationDateDescription = MutableLiveData<String>()
    val cardNameDescription = MutableLiveData<String>()

    fun setCardNumberDescription(text: String) {
        cardNumberDescription.value = text
        validateFields()
    }

    fun setCvvNumberDescription(text: String) {
        cvvNumberDescription.value = text
        validateFields()
    }

    fun setExpirationDateDescription(text: String) {
        expirationDateDescription.value = text
        validateFields()
    }

    fun setCardNameDescription(text: String) {
        cardNameDescription.value = text
        validateFields()
    }

    private val paymentResult = object : IPaymentResult {
        override fun setInformationRequest(paymentResponse: PaymentResponse) {
            ViewManager.getInstance.goTo(NotificationActivity::class.java,  Bundle().apply {
                putString(NotificationActivity.PAYMENT_RESPONSE, paymentResponse.json())
            })
            clearView()
        }
    }

    fun clearView() {
        cardName.value = ""
        cardNumber.value = ""
        cvvNumber.value = ""
        expirationDate.value = ""
        totalPayment.value = ""
        buttonPaymentEnabled.value = false
    }

    fun paymentButtonClick() {
        val card = Card(
            number = cardNumber.value ?: "", //"36545400000008"
            cvv = cvvNumber.value ?: "",//"123",
            expirationMonth = expirationDate.value?.let { it.split("/")[0] } ?: run { "" },//"11",
            expirationYear = expirationDate.value?.let { it.split("/")[1] } ?: run { "" } //"22"
        )

        val payment = Payment(
            reference = "Test",
            amount = Amount(
                currency = "COP",
                total = totalPayment.value?.let { it.toDouble() } ?: run { 0.0 } //500000.0
            )
        )

        val arrayName = cardName.value?.split(" ") ?: run { arrayListOf<String>() }
        val payer = Person(
            documentType = "CC",
            name = arrayName[0],//"Edson",
            email = settingsSharedPreferences.getCurrentUser().email,
            document = "123456789",
            surname = if (arrayName.size > 2) arrayName[1] else ""//"Nieto"
        )
        paymentDomain.errorManager = this
        paymentDomain.domainResult(paymentResult)
        paymentDomain.processTransaction(payer, payment, card, NetworkUtil.getIpAddress())
    }

    fun validateFields() {
        buttonPaymentEnabled.value = true
        if (cardName.value.isNullOrEmpty()
            || cardNumber.value.isNullOrEmpty()
            || cvvNumber.value.isNullOrEmpty()
            || expirationDate.value.isNullOrEmpty()
            || totalPayment.value.isNullOrEmpty()
        ) {
            buttonPaymentEnabled.value = false
        }
    }

    fun getStringDate(value: String): String {
        val copyDate = ViewManager.getInstance.getString(R.string.card_date)
        val newDate = copyDate.substring(0, if (value.length == 1) value.length + 1 else value.length)
        return copyDate.replace(newDate, if (value.length == 1) value + "M" else value)
    }

    fun cleanDateString(value: String): String {
        var newValue = ""
        value.forEach { char ->
            if (char.isDigit()) {
                newValue += char
            }
        }
        if (newValue.length > 2) {
            newValue = newValue.substring(0, 2) + DATE_DIVIDER + newValue.substring(2)
        }
        return if (newValue.length > 5) newValue.substring(0, 5) else newValue
    }

    companion object {
        const val DATE_DIVIDER = "/"
        const val DATE_EMPTY = ""
        const val DATE_MM_YY = "MM/YY"
    }
}