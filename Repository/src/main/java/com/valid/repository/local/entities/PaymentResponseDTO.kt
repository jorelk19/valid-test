package com.valid.repository.local.entities

import io.realm.RealmObject

open class PaymentResponseDTO : RealmObject() {
    private var _id :Int = 0
    private var _email = ""
    private var _status: StatusDTO = StatusDTO()
    private var _date: String = ""
    private var _transactionDate: String = ""
    private var _internalReference: Int = 0
    private var _reference: String = ""
    private var _paymentMethod: String = ""
    private var _franchise: String = ""
    private var _franchiseName: String = ""
    private var _issuerName: String = ""
    private var _amount: AmountDTO = AmountDTO()
    private var _authorization: String = ""
    private var _receipt: String = ""

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


    var status: StatusDTO
        get() {
            return _status
        }
        set(value) {
            _status = value
        }

    var date: String
        get() {
            return _date
        }
        set(value) {
            _date = value
        }

    var transactionDate: String
        get() {
            return _transactionDate
        }
        set(value) {
            _transactionDate = value
        }

    var internalReference: Int
        get() {
            return _internalReference
        }
        set(value) {
            _internalReference = value
        }

    var reference: String
        get() {
            return _reference
        }
        set(value) {
            _reference = value
        }

    var paymentMethod: String
        get() {
            return _paymentMethod
        }
        set(value) {
            _paymentMethod = value
        }

    var franchise: String
        get() {
            return _franchise
        }
        set(value) {
            _franchise = value
        }

    var franchiseName: String
        get() {
            return _franchiseName
        }
        set(value) {
            _franchiseName = value
        }

    var issuerName: String
        get() {
            return _issuerName
        }
        set(value) {
            _issuerName = value
        }

    var amount: AmountDTO
        get() {
            return _amount
        }
        set(value) {
            _amount = value
        }

    var authorization: String
        get() {
            return _authorization
        }
        set(value) {
            _authorization = value
        }

    var receipt: String
        get() {
            return _receipt
        }
        set(value) {
            _receipt = value
        }
}