package com.valid.repository.interfaces

import com.valid.businessmodels.business.User

interface IDefaultUserRepository {
    fun saveDefaultUser()
    fun create(user: User)
}