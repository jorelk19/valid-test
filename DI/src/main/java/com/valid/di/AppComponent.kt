package com.valid.di

import com.valid.domain.MyPaymentsDomain
import com.valid.domain.PaymentDomain
import com.valid.domain.SignInDomain
import com.valid.domain.SplashScreenDomain
import com.valid.repository.implementation.PaymentRepository
import com.valid.repository.implementation.SignInRepository

/**
 * Class used to manage the components from application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
data class AppComponent(
    val signInRepository: SignInRepository,
    val paymentRepository: PaymentRepository,
    val signInDomain: SignInDomain,
    val splashScreenDomain: SplashScreenDomain,
    val paymentDomain: PaymentDomain,
    val myPaymentsDomain: MyPaymentsDomain
)
