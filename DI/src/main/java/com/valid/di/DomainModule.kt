package com.valid.di

import com.valid.domain.MyPaymentsDomain
import com.valid.domain.PaymentDomain
import com.valid.domain.SignInDomain
import com.valid.domain.SplashScreenDomain
import com.valid.repository.implementation.DefaultUserLocalRepository
import com.valid.repository.implementation.PaymentLocalRepository
import com.valid.repository.implementation.PaymentRepository
import com.valid.repository.implementation.SignInRepository
import org.koin.dsl.module

/**
 * Variable used to load the domain modules that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
val domainModule = module {
    single { provideSignInDomain(get()) }
    single { provideSplashScreenDomain(get()) }
    single { providePaymentDomain(get(), get()) }
    single { provideMyPaymentsDomain(get(), get()) }
}

private fun provideSignInDomain(signInRepository: SignInRepository) = SignInDomain(signInRepository)
private fun provideSplashScreenDomain(defaultUserLocalRepository: DefaultUserLocalRepository) = SplashScreenDomain(defaultUserLocalRepository)
private fun providePaymentDomain(paymentRepository: PaymentRepository, paymentLocalRepository : PaymentLocalRepository) = PaymentDomain(paymentRepository, paymentLocalRepository)
private fun provideMyPaymentsDomain(paymentRepository: PaymentRepository, paymentLocalRepository : PaymentLocalRepository) = MyPaymentsDomain(paymentRepository, paymentLocalRepository)

