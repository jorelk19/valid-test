package com.valid.di

import com.valid.businessmodels.api.RadioApi
import com.valid.repository.implementation.DefaultCountryLocalRepository
import com.valid.repository.remote.RemoteRepository
import org.koin.dsl.module

/**
 * Variable used to load the repository modules that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */


private fun provideDefaultCountryRepository() = DefaultCountryLocalRepository()
private fun provideRemoteRepository(radioApi: RadioApi) = RemoteRepository(radioApi)

val repositoryModule = module {
    single { provideDefaultCountryRepository() }
    single { provideRemoteRepository(get()) }
}


