package com.valid.di

import com.valid.domain.SplashScreenDomain
import com.valid.domain.ArtistDomain
import com.valid.repository.implementation.DefaultCountryLocalRepository
import com.valid.repository.remote.RemoteRepository
import org.koin.dsl.module

/**
 * Variable used to load the domain modules that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
val domainModule = module {
    single { provideSplashScreenDomain(get()) }
    single { provideTrackDomain(get()) }
}

private fun provideSplashScreenDomain(defaultCountryLocalRepository: DefaultCountryLocalRepository) = SplashScreenDomain(defaultCountryLocalRepository)
private fun provideTrackDomain(remoteRepository: RemoteRepository) = ArtistDomain(remoteRepository)


