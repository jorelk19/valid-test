package com.valid.repository.local.mapper

import com.valid.businessmodels.business.Amount
import com.valid.businessmodels.business.Status
import com.valid.businessmodels.response.PaymentResponse
import com.valid.repository.local.entities.AmountDTO
import com.valid.repository.local.entities.PaymentResponseDTO
import com.valid.repository.local.entities.StatusDTO


object PaymentRepositoryPaymentResponseMapper {
    fun mapPaymentResponseDTO(paymentResponse: PaymentResponse): PaymentResponseDTO {
        val paymentResponseDTO = PaymentResponseDTO()
        paymentResponse.amount?.let {
            paymentResponseDTO.amount = mapAmountDTO(it)
        }
        paymentResponseDTO.email = paymentResponse.email
        paymentResponse.authorization?.let {
            paymentResponseDTO.authorization = it
        }
        paymentResponse.date?.let {
            paymentResponseDTO.date = it
        }
        paymentResponseDTO.franchise = paymentResponse.franchise
        paymentResponseDTO.franchiseName = paymentResponse.franchiseName
        paymentResponseDTO.internalReference = paymentResponse.internalReference
        paymentResponse.issuerName?.let {
            paymentResponseDTO.issuerName = it
        }
        paymentResponse.paymentMethod?.let {
            paymentResponseDTO.paymentMethod = it
        }
        paymentResponse.receipt?.let {
            paymentResponseDTO.receipt = it
        }
        paymentResponse.reference?.let {
            paymentResponseDTO.reference = it
        }
        paymentResponseDTO.status = mapStatusDTO(paymentResponse.status)
        paymentResponse.transactionDate?.let {
            paymentResponseDTO.transactionDate = it
        }
        return paymentResponseDTO
    }

    fun mapAmountDTO(amount: Amount): AmountDTO {
        val amountDto = AmountDTO()
        amountDto.currency = amount.currency
        amountDto.total = amount.total
        return amountDto
    }

    fun mapStatusDTO(status: Status): StatusDTO {
        val statusDTO = StatusDTO()
        statusDTO.date = status.date
        statusDTO.message = status.message
        statusDTO.reason = status.reason
        statusDTO.status = status.status
        return statusDTO
    }

    fun mapPaymentResponseBusiness(paymentResponseDTO: PaymentResponseDTO): PaymentResponse {
        val paymentResponse = PaymentResponse()
        paymentResponse.amount = mapAmountBusiness(paymentResponseDTO.amount)
        paymentResponse.email = paymentResponseDTO.email
        paymentResponse.authorization = paymentResponseDTO.authorization
        paymentResponse.date = paymentResponseDTO.date
        paymentResponse.franchise = paymentResponseDTO.franchise
        paymentResponse.franchiseName = paymentResponseDTO.franchiseName
        paymentResponse.internalReference = paymentResponseDTO.internalReference
        paymentResponse.issuerName = paymentResponseDTO.issuerName
        paymentResponse.paymentMethod = paymentResponseDTO.paymentMethod
        paymentResponse.receipt = paymentResponseDTO.receipt
        paymentResponse.reference = paymentResponseDTO.reference
        paymentResponse.status = mapStatusBusiness(paymentResponseDTO.status)
        paymentResponse.transactionDate = paymentResponse.transactionDate
        return paymentResponse
    }

    fun mapAmountBusiness(amountDTO: AmountDTO): Amount {
        return Amount(
            currency = amountDTO.currency,
            total = amountDTO.total
        )
    }

    fun mapStatusBusiness(statusDTO: StatusDTO): Status {
        val status = Status()
        status.date = statusDTO.date
        status.message = statusDTO.message
        status.reason = statusDTO.reason
        status.status = statusDTO.status
        return status
    }
}