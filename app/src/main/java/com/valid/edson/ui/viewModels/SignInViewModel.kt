package com.valid.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.response.SignInResponse
import com.valid.businessmodels.result.ISignInResult
import com.valid.di.app.App
import com.valid.domain.SignInDomain
import com.valid.edson.R
import com.valid.edson.utils.settingsSharedPreferences
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.edson.ui.views.activities.MainActivity
import com.valid.utils.SnackFactory
import com.valid.utils.ViewManager
import com.valid.edson.utils.isValidEmail

class SignInViewModel(private val sigInDomain: SignInDomain) : BaseViewModel() {
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isButtonEnabled = MutableLiveData<Boolean>()
    val emailError = MutableLiveData<String>()

    fun getUserName(): LiveData<String> {
        return userName
    }

    fun getPassword(): LiveData<String> {
        return password
    }

    fun getEmailError(): LiveData<String> {
        return emailError
    }

    fun onSignInClick() {
        sigInDomain.errorManager = this
        sigInDomain.hasInternet = ViewManager.getInstance.hasInternet()
        sigInDomain.domainResult(signInResult)
        sigInDomain.signIn(userName = userName.value!!, password = password.value!!)
    }

    private val signInResult = object : ISignInResult {
        override fun logIn(signInResponse: SignInResponse) {
            if (signInResponse.isValid) {
                settingsSharedPreferences.setCurrentUser(signInResponse.user)
                ViewManager.getInstance.goTo(MainActivity::class.java)
            } else {
                SnackFactory.showWarningMessage(ViewManager.getInstance.getCurrentActivity(), R.id.coordinatoar_login, ViewManager.getInstance.getString(R.string.login_service_error), currentContext = App.getAppContext())
            }
        }
    }

    fun validateEnabledLogin() {
        isButtonEnabled.value = !(userName.value.isNullOrEmpty() || password.value.isNullOrEmpty())
        userName.value?.isValidEmail(
            onTrue = {
                emailError.value = ""
            },
            onFalse = {
                emailError.value = ViewManager.getInstance.getString(R.string.email_is_empty)
                isButtonEnabled.value = false
            },
            onEmptyString = {
                emailError.value = ViewManager.getInstance.getString(R.string.email_is_invalid)
                isButtonEnabled.value = false
            }
        )
    }
}