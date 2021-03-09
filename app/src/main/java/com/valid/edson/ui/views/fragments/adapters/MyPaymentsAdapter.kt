package com.valid.edson.ui.views.fragments.adapters

import android.content.Context
import com.valid.businessmodels.response.PaymentResponse
import com.valid.edson.R
import com.valid.edson.databinding.LayoutMyPaymentItemBinding
import com.valid.utils.GenericAdapter
import com.valid.edson.utils.toCurrencyFormat

class MyPaymentsAdapter(context: Context, myPayments : ArrayList<PaymentResponse>) : GenericAdapter<PaymentResponse, LayoutMyPaymentItemBinding>(context, myPayments) {
    override fun getLayoutResId(): Int {
        return R.layout.layout_my_payment_item
    }

    override fun onBindData(model: PaymentResponse, position: Int, dataBinding: LayoutMyPaymentItemBinding) {
        dataBinding.tvInternalReference.text = model.internalReference.toString()
        dataBinding.tvMyPaymentState.text = model.status.message
        dataBinding.tvMyPaymentTotalAmount.text = model.amount!!.total.toCurrencyFormat()
        dataBinding.tvPaymentDate.text = model.date
    }

}