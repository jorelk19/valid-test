package com.valid.di

import com.valid.businessmodels.api.PaymentApi
import com.valid.repository.implementation.DefaultUserLocalRepository
import com.valid.repository.implementation.PaymentLocalRepository
import com.valid.repository.implementation.PaymentRepository
import com.valid.repository.implementation.SignInRepository
import org.koin.dsl.module

/**
 * Variable used to load the repository modules that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */

private fun provideSignInRepository() = SignInRepository()
private fun providePaymentRepository(paymentApi: PaymentApi) = PaymentRepository(paymentApi)
private fun providePaymentLocalRepository() = PaymentLocalRepository()
private fun provideDefaultUserRepository() = DefaultUserLocalRepository()

val repositoryModule = module {
    single { provideSignInRepository() }
    single { provideDefaultUserRepository() }
    single { providePaymentRepository(get()) }
    single { providePaymentLocalRepository() }
}


