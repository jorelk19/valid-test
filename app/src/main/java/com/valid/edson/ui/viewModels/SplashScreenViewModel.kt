package com.valid.edson.ui.viewModels

import com.valid.domain.SplashScreenDomain
import com.valid.edson.ui.viewModels.base.BaseViewModel

class SplashScreenViewModel(private val splashScreenDomain: SplashScreenDomain) : BaseViewModel() {
    fun createDefaultUser(){
        splashScreenDomain.errorManager = this
        splashScreenDomain.createDefaultUser()
    }
}