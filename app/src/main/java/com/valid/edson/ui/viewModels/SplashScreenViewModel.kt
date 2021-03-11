package com.valid.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.business.Country
import com.valid.businessmodels.result.ISplashScreenResult
import com.valid.domain.SplashScreenDomain
import com.valid.edson.ui.viewModels.base.BaseViewModel

class SplashScreenViewModel(private val splashScreenDomain: SplashScreenDomain) : BaseViewModel() {
    private var currentCountries = MutableLiveData<ArrayList<Country>>()

    private val splashScreenResult = object : ISplashScreenResult {
        override fun getDefaultCountries(countries: ArrayList<Country>) {
            currentCountries.value = countries
        }
    }

    fun getAllCountries() : LiveData<ArrayList<Country>> {
        return currentCountries
    }

    fun createDefaultUser(){
        splashScreenDomain.errorManager = this
        splashScreenDomain.domainResult(splashScreenResult)
        splashScreenDomain.createDefaultUser()
    }

    fun getAllCountriesFromRepository(){
        splashScreenDomain.getAllCountries()
    }
}