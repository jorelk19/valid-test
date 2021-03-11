package com.valid.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.business.Country
import com.valid.edson.ui.viewModels.base.BaseViewModel

class CountryViewModel : BaseViewModel() {
    var currentCountries = MutableLiveData<ArrayList<Country>>()

    /**
     * Method to get the sites live data to show into view
     * */
    fun getCountries(): LiveData<ArrayList<Country>> {
        return currentCountries
    }

    fun setCurrentCountries(countries : ArrayList<Country>){
        currentCountries.value = countries
    }
}