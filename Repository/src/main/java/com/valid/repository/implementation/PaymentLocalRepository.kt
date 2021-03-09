package com.valid.repository.implementation

import com.valid.businessmodels.response.PaymentResponse
import com.valid.repository.RepositoryConfiguration
import com.valid.repository.local.ILocalRepositoryManager
import com.valid.repository.local.entities.PaymentResponseDTO
import com.valid.repository.local.mapper.PaymentRepositoryPaymentResponseMapper
import io.realm.Realm

class PaymentLocalRepository : ILocalRepositoryManager<PaymentResponse, PaymentResponseDTO> {
    private var realm: Realm = RepositoryConfiguration.getInstance()

    override fun update(paymentResponse: PaymentResponse) {
        val paymentResponseDTO = PaymentRepositoryPaymentResponseMapper.mapPaymentResponseDTO(paymentResponse)
        paymentResponseDTO.status = PaymentRepositoryPaymentResponseMapper.mapStatusDTO(paymentResponse.status)
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(paymentResponseDTO)
        realm.commitTransaction()
    }

    override fun create(paymentResponse: PaymentResponse) {
        val index = realm.where(PaymentResponseDTO::class.java).max("_id")
        val paymentResponseDTO = PaymentRepositoryPaymentResponseMapper.mapPaymentResponseDTO(paymentResponse)
        index?.let { num ->
            paymentResponseDTO.id = num.toInt() + 1
        } ?: run { paymentResponseDTO.id = 1 }

        realm.executeTransaction { currentRealm ->
            currentRealm.copyToRealm(paymentResponseDTO)
        }
    }

    override fun delete(paymentResponse: PaymentResponse) {}

    override fun read(paymentResponse: PaymentResponse): PaymentResponse {
        val existPaymentResponse = realm.where(PaymentResponseDTO::class.java).equalTo("_internalReference", paymentResponse.internalReference).findFirst()
        return existPaymentResponse?.let { PaymentRepositoryPaymentResponseMapper.mapPaymentResponseBusiness(it) } ?: run { PaymentResponse() }
    }

    override fun removeAll(paymentResponse: PaymentResponseDTO) {}

    override fun getAll(paymentResponse: PaymentResponse): ArrayList<PaymentResponse> {
        val paymentResponseDTOList = realm.where(PaymentResponseDTO::class.java).equalTo("_email", paymentResponse.email).findAll()
        val paymentResponseList = ArrayList<PaymentResponse>()
        paymentResponseDTOList.forEach {
            paymentResponseList.add(PaymentRepositoryPaymentResponseMapper.mapPaymentResponseBusiness(it))
        }
        return paymentResponseList
    }
}