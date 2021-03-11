package com.valid.repository.local.entities

import io.realm.RealmObject

open class CountryDTO : RealmObject() {
    private var _id: Int = 0
    private var _countryName: String = ""
    private var _countryCode: String = ""

    var id: Int
        get() {
            return _id
        }
        set(value) {
            _id = value
        }

    var countryName: String
        get() {
            return _countryName
        }
        set(value) {
            _countryName = value
        }

    var countryCode: String
        get() {
            return _countryCode
        }
        set(value) {
            _countryCode = value
        }
}