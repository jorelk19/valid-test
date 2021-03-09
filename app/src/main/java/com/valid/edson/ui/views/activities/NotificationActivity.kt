package com.valid.edson.ui.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.valid.edson.databinding.ActivityNotificationBinding
import com.valid.businessmodels.response.PaymentResponse
import com.valid.edson.R
import com.valid.edson.utils.getViewModelFactory
import com.valid.edson.ui.viewModels.NotificationViewModel
import com.valid.edson.ui.views.activities.base.BaseFragmentActivity
import com.valid.utils.fromJson

class NotificationActivity : BaseFragmentActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private val viewModel by viewModels<NotificationViewModel> { getViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@NotificationActivity, R.layout.activity_notification)
        binding.viewModel = viewModel
        getExtras()
        addSubscriptions()
    }

    private fun addSubscriptions() {
        binding.ivClose.setColorFilter(R.color.fontDarkGray)
        viewModel.getImageState().observe(this, Observer {
            binding.ivCheck.setImageDrawable(it)
        })
    }

    private fun getExtras() {
        intent.extras?.let {
            it.getString(PAYMENT_RESPONSE)?.let {
                val paymentResponse = it.fromJson<PaymentResponse>()
                viewModel.setNotificationInformation(paymentResponse)
            }
        }
    }

    companion object {
        const val PAYMENT_RESPONSE = "PAYMENT_RESPONSE"
    }
}