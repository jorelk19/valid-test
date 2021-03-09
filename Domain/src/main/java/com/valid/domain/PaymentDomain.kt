package com.valid.domain

import com.valid.businessmodels.business.Person
import com.valid.businessmodels.business.Payment
import com.valid.businessmodels.response.PaymentResponse
import com.valid.businessmodels.result.IPaymentResult
import com.valid.domain.base.DomainManager
import com.valid.domain.utils.AuthenticationManager
import com.valid.repository.interfaces.IPaymentRepository
import com.valid.repository.local.ILocalRepositoryManager
import com.valid.repository.local.entities.PaymentResponseDTO
import com.valid.utils.security.CryptoTools
import com.valid.businessmodels.request.Card
import com.valid.businessmodels.request.Credit
import com.valid.businessmodels.request.Instrument
import com.valid.businessmodels.request.PaymentRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class PaymentDomain(private val paymentRepository: IPaymentRepository, private val paymentLocalRepository: ILocalRepositoryManager<PaymentResponse, PaymentResponseDTO>) : DomainManager<IPaymentResult>() {
    private lateinit var currentPaymentResult: IPaymentResult
    override fun domainResult(paymentResult: IPaymentResult) {
        currentPaymentResult = paymentResult
    }

    fun processTransaction(payer: Person, payment: Payment, currentCard: Card, ipAddress : String) {
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                val nonce = CryptoTools.getPlainNonce()
                val paymentRequest = PaymentRequest(
                    auth = AuthenticationManager.buildAuthentication(nonce),
                    instrument = Instrument(
                        card = currentCard,
                        credit = Credit()
                    ),
                    payment = payment,
                    payer = payer,
                    buyer = payer,
                    ipAddress = ipAddress
                )
                val paymentResponse = withContext(Dispatchers.IO) { paymentRepository.processTransaction(paymentRequest) }
                paymentResponse.email = payer.email
                paymentLocalRepository.create(paymentResponse)
                currentPaymentResult.setInformationRequest(paymentResponse)
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