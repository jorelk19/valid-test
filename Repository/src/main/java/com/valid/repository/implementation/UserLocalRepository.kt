package com.valid.repository.implementation

import com.valid.repository.RepositoryConfiguration
import com.valid.repository.local.ILocalRepositoryManager
import com.valid.repository.local.entities.CountryDTO
import com.valid.repository.local.mapper.LocalRepositoryCountryMapper
import io.realm.Realm
/*
class UserLocalRepository : ILocalRepositoryManager<User, CountryDTO> {
    private var realm: Realm = RepositoryConfiguration.getInstance()

    override fun read(currentUser: User): User {
        val userExists = validateUserDtoExists(currentUser)
        var userBusiness = User()
        userExists?.let {
            userBusiness = LocalRepositoryCountryMapper.mapCountryBusiness(it)
        } ?: run { throw Exception("Los datos del usuario ingresado no existen") }
        return userBusiness
    }

    override fun removeAll(element: CountryDTO) {
    }

    override fun getAll(element: User): ArrayList<User> {
        return arrayListOf()
    }

    override fun update(user: User) {
        val userExists = validateUserDtoExists(user)
        userExists?.let { currentUserDTO ->
            currentUserDTO.email = user.email
            currentUserDTO.cellPhoneNumber = user.cellPhoneNumber
            currentUserDTO.password = user.password
            currentUserDTO.firstName = user.firstName
            currentUserDTO.lastName = user.lastName
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(currentUserDTO)
            realm.commitTransaction()
        }
    }

    private fun validateUserDtoExists(user: User) = realm.where(CountryDTO::class.java).equalTo("_email", user.email).findFirst()

    override fun create(user: User) {
        val userExists = validateUserDtoExists(user)
        userExists?.let {
            throw Exception("El correo electrónico que intenta registrar ya existe")
        } ?: run {
            val index = realm.where(CountryDTO::class.java).max("_id")
            val userDto = LocalRepositoryCountryMapper.mapCountryDTO(user)
            index?.let { num ->
                userDto.id = num.toInt() + 1
            } ?: run { userDto.id = 1 }

            realm.executeTransaction { currentRealm ->
                currentRealm.copyToRealm(userDto)
            }
        }
    }

    override fun delete(user: User) {
        val userExists = validateUserDtoExists(user)
        userExists?.let { userDto ->
            realm.executeTransaction {
                userDto.deleteFromRealm()
            }
        } ?: run {
            throw Exception("El usuario que intenta borrar no existe")
        }
    }
}*/
