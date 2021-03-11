package com.valid.edson.ui.viewModels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.valid.di.KoinManager
import com.valid.edson.ui.viewModels.*

/**
 * Class used to create view models through factory pattern
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    /**
     * Method to return instance from specific view model
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass)
        {
            when {
                isAssignableFrom(SplashScreenViewModel::class.java) -> SplashScreenViewModel(KoinManager.getAppComponent().splashScreenDomain)
                isAssignableFrom(CountryViewModel::class.java) -> CountryViewModel()
                isAssignableFrom(TrackViewModel::class.java) -> TrackViewModel(KoinManager.getAppComponent().trackDomain)
                else -> throw IllegalStateException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}