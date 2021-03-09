package com.valid.repository.local.entities

import io.realm.RealmObject

open class AmountDTO: RealmObject() {
    private var _currency: String = ""
    private var _total: Double = 0.0

    var currency: String
        get() {
            return _currency
        }
        set(value) {
            _currency = value
        }

    var total: Double
        get() {
            return _total
        }
        set(value) {
            _total = value
        }
}