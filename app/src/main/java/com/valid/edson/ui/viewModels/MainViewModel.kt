package com.valid.edson.ui.viewModels

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.valid.businessmodels.business.Country
import com.valid.edson.R
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.edson.ui.views.fragments.CountryFragment
import com.valid.utils.ViewManager
import com.valid.utils.json

class MainViewModel : BaseViewModel() {

    private val currentCountries = MutableLiveData<ArrayList<Country>>()

    fun setCurrentCountries(countries: ArrayList<Country>) {
        currentCountries.value = countries
    }

    fun showCountryFragmentClick() {
        val countryFragment = CountryFragment()
        countryFragment.arguments = Bundle().apply { putString(CountryFragment.CURRENT_COUNTRIES, currentCountries.value?.json()) }
        ViewManager.getInstance.attachFragment(countryFragment, R.id.main_container_layout, addNewTransaction = false, addToBackStack = false)
    }
}