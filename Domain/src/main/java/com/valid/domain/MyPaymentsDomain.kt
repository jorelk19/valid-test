package com.valid.domain

import com.valid.businessmodels.response.PaymentResponse
import com.valid.businessmodels.result.IMyPaymentsResult
import com.valid.domain.base.DomainManager
import com.valid.repository.interfaces.IPaymentRepository
import com.valid.repository.local.ILocalRepositoryManager
import com.valid.repository.local.entities.PaymentResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class MyPaymentsDomain(private val paymentRepository: IPaymentRepository, private val paymentLocalRepository: ILocalRepositoryManager<PaymentResponse, PaymentResponseDTO>) : DomainManager<IMyPaymentsResult>() {
    private lateinit var currentMyPaymentsResult: IMyPaymentsResult
    override fun domainResult(myPaymentsResult: IMyPaymentsResult) {
        currentMyPaymentsResult = myPaymentsResult
    }

    fun getLocalPayments(userEmail: String) {
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                val paymentResponse = PaymentResponse(
                    email = userEmail
                )
                val payments = paymentLocalRepository.getAll(paymentResponse)
                currentMyPaymentsResult.setMyPayments(payments)
                errorManager.onHideLoader()
            } catch (exception: HttpException) {
                errorManager.onServiceErrorHttpException(exception.message, exception)
            } catch (exception: SocketTimeoutException) {
                errorManager.onSocketTimeoutException(exception.message)
            } catch (exception: IOException) {
                errorManager.onSocketTimeoutException(exception.message)
            } catch (exception: Exception) {
                errorManager.onSocketTimeoutException(exception.message)
            }
        }
    }
}