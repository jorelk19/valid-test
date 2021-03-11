package com.valid.di

import com.valid.domain.SplashScreenDomain
import com.valid.domain.TrackDomain
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Class used to manage the koin component that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class RadioComponent : KoinComponent {
    private val splashScreenDomain : SplashScreenDomain by inject()
    private val trackDomain : TrackDomain by inject()
    val appComponent = AppComponent (
        splashScreenDomain = splashScreenDomain,
        trackDomain = trackDomain
    )
}
