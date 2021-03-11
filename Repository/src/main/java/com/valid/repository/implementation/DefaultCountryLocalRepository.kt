package com.valid.repository.implementation

import com.valid.businessmodels.business.Country
import com.valid.repository.RepositoryConfiguration
import com.valid.repository.interfaces.IDefaultCountryRepository
import com.valid.repository.local.entities.CountryDTO
import com.valid.repository.local.mapper.LocalRepositoryCountryMapper
import io.realm.Realm

class DefaultCountryLocalRepository : IDefaultCountryRepository {
    private var realm: Realm = RepositoryConfiguration.getInstance()

    private fun validateCountryDtoExists(country: Country) = realm.where(CountryDTO::class.java).equalTo("_id", country.countryId).findFirst()

    override fun getAll(): ArrayList<Country> {
        val countryResponseDTOList = realm.where(CountryDTO::class.java).findAll()
        val countryResponseList = ArrayList<Country>()
        countryResponseDTOList.forEach {
            countryResponseList.add(LocalRepositoryCountryMapper.mapCountryBusiness(it))
        }
        return countryResponseList
    }

    override fun create(country: Country) {
        val countryExists = validateCountryDtoExists(country)
        if (countryExists == null) {
            val index = realm.where(CountryDTO::class.java).max("_id")
            val countryDto = LocalRepositoryCountryMapper.mapCountryDTO(country)
            index?.let { num ->
                countryDto.id = num.toInt() + 1
            } ?: run { countryDto.id = 1 }

            realm.executeTransaction { currentRealm ->
                currentRealm.copyToRealm(countryDto)
            }
        }
    }

    override fun saveDefaultCountries() {
        val defaultCountries = ArrayList<Country>()
        defaultCountries.add(Country(countryId = 1, countryCode = "CO", countryName = "Colombia"))
        defaultCountries.add(Country(countryId = 2, countryCode = "SP", countryName = "Spain"))
        defaultCountries.add(Country(countryId = 3, countryCode = "MX", countryName = "Mexico"))
        defaultCountries.add(Country(countryId = 4, countryCode = "UK", countryName = "United kingdom"))
        defaultCountries.add(Country(countryId = 5, countryCode = "US", countryName = "United states"))
        defaultCountries.add(Country(countryId = 6, countryCode = "AR", countryName = "Argentina"))

        defaultCountries.forEach { country ->
            val countryExists = validateCountryDtoExists(country)
            if (countryExists == null) {
                create(country)
            }
        }
    }
}