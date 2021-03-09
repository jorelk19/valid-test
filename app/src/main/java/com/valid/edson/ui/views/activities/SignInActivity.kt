package com.valid.edson.ui.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.valid.edson.R
import com.valid.edson.databinding.ActivitySignInBinding
import com.valid.edson.utils.getViewModelFactory
import com.valid.edson.ui.viewModels.SignInViewModel
import com.valid.edson.ui.views.activities.base.BaseFragmentActivity

class SignInActivity : BaseFragmentActivity() {
    private val signInViewModel by viewModels<SignInViewModel> { getViewModelFactory() }
    private lateinit var activitySignInBinding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySignInBinding = DataBindingUtil.setContentView(this@SignInActivity, R.layout.activity_sign_in)
        activitySignInBinding.lifecycleOwner = this@SignInActivity
        activitySignInBinding.viewModel = signInViewModel
        addSubscriptions()
    }

    private fun addSubscriptions() {
        signInViewModel.getUserName().observe(this, Observer {
            signInViewModel.validateEnabledLogin()
        })

        signInViewModel.getPassword().observe(this, Observer {
            signInViewModel.validateEnabledLogin()
        })

        signInViewModel.getEmailError().observe(this, Observer { error ->
            activitySignInBinding.tInputEmail.error = error
        })
    }
}