package com.valid.edson.ui.viewModels

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.response.PaymentResponse
import com.valid.edson.R
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.utils.ViewManager
import com.valid.edson.utils.toCurrencyFormat

class NotificationViewModel : BaseViewModel() {
    var internalReference =  MutableLiveData<String>()
    var transactionState =  MutableLiveData<String>()
    var totalAmount =  MutableLiveData<String>()
    var imageState =  MutableLiveData<Drawable>()

    fun getImageState() : LiveData<Drawable> = imageState

    fun setNotificationInformation(paymentResponse: PaymentResponse){
        internalReference.value = paymentResponse.internalReference.toString()
        transactionState.value = paymentResponse.status.message
        totalAmount.value = paymentResponse.amount!!.total.toCurrencyFormat()
        imageState.value = if(paymentResponse.status.status != "APPROVED") ViewManager.getInstance.getDrawable(R.drawable.ic_error) else ViewManager.getInstance.getDrawable(R.drawable.ic_check_circle)
    }

    fun closeWindowClick(){
        ViewManager.getInstance.getCurrentActivity().finish()
    }
}