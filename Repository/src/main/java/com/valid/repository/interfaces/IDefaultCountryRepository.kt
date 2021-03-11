package com.valid.repository.interfaces

import com.valid.businessmodels.business.Country

interface IDefaultCountryRepository {
    fun saveDefaultCountries()
    fun create(country: Country)
    fun getAll() : ArrayList<Country>
}