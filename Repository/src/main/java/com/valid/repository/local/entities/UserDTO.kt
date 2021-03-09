package com.valid.repository.local.entities

import io.realm.RealmObject

open class UserDTO : RealmObject() {
    private var _id: Int = 0
    private var _email: String = ""
    private var _firstName: String = ""
    private var _lastName: String = ""
    private var _password: String = ""
    private var _cellPhoneNumber: String = ""

    var id: Int
        get() {
            return _id
        }
        set(value) {
            _id = value
        }

    var email: String
        get() {
            return _email
        }
        set(value) {
            _email = value
        }

    var firstName: String
        get() {
            return _firstName
        }
        set(value) {
            _firstName = value
        }

    var lastName: String
        get() {
            return _lastName
        }
        set(value) {
            _lastName = value
        }

    var password: String
        get() {
            return _password
        }
        set(value) {
            _password = value
        }

    var cellPhoneNumber: String
        get() {
            return _cellPhoneNumber
        }
        set(value) {
            _cellPhoneNumber = value
        }
}