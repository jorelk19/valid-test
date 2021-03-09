package com.valid.repository.implementation

import com.valid.businessmodels.business.User
import com.valid.repository.RepositoryConfiguration
import com.valid.repository.interfaces.IDefaultUserRepository
import com.valid.repository.local.entities.UserDTO
import com.valid.repository.local.mapper.LocalRepositoryUserMapper
import io.realm.Realm

class DefaultUserLocalRepository : IDefaultUserRepository {
    private var realm: Realm = RepositoryConfiguration.getInstance()

    private fun validateUserDtoExists(user: User) = realm.where(UserDTO::class.java).equalTo("_email", user.email).findFirst()

    override fun create(user: User) {
        val userExists = validateUserDtoExists(user)
        if(userExists == null){
            val index = realm.where(UserDTO::class.java).max("_id")
            val userDto = LocalRepositoryUserMapper.mapUserDTO(user)
            index?.let { num ->
                userDto.id = num.toInt() + 1
            } ?: run { userDto.id = 1 }

            realm.executeTransaction { currentRealm ->
                currentRealm.copyToRealm(userDto)
            }
        }
    }

    override fun saveDefaultUser() {
        val user = User(firstName = "Edson", lastName = "Nieto", email = "jorelk19@gmail.com", cellPhoneNumber = "3012960834", password = "Evan2017")
        val userExists = validateUserDtoExists(user)
        if (userExists == null) {
            create(user)
        }
    }
}