package com.valid.di

import com.valid.domain.SplashScreenDomain
import com.valid.domain.TrackDomain

/**
 * Class used to manage the components from application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
data class AppComponent(
    val splashScreenDomain: SplashScreenDomain,
    val trackDomain: TrackDomain
)
