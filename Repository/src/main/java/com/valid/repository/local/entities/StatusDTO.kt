package com.valid.repository.local.entities

import io.realm.RealmObject

open class StatusDTO: RealmObject() {
    private var _status: String = ""
    private var _reason: String = ""
    private var _message: String = ""
    private var _date: String = ""

    var status: String
        get() {
            return _status
        }
        set(value) {
            _status = value
        }

    var reason: String
        get() {
            return _reason
        }
        set(value) {
            _reason = value
        }

    var message: String
        get() {
            return _message
        }
        set(value) {
            _message = value
        }

    var date: String
        get() {
            return _date
        }
        set(value) {
            _date = value
        }
}