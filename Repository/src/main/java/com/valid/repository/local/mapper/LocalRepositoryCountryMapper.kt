package com.valid.repository.local.mapper

import com.valid.businessmodels.business.Country
import com.valid.repository.local.entities.CountryDTO

class LocalRepositoryCountryMapper {
    companion object {
        fun mapCountryDTO(country: Country): CountryDTO {
            val countryDTO = CountryDTO()
            countryDTO.countryCode = country.countryCode
            countryDTO.countryName = country.countryName
            countryDTO.id = country.countryId
            return countryDTO
        }

        fun mapCountryBusiness(countryDTO: CountryDTO): Country {
            return Country(
                countryId = countryDTO.id,
                countryName = countryDTO.countryName,
                countryCode = countryDTO.countryCode
            )
        }
    }
}