package com.valid.businessmodels.result

import com.valid.businessmodels.business.Country

interface  ISplashScreenResult {
    fun getDefaultCountries(countries : ArrayList<Country>)
}