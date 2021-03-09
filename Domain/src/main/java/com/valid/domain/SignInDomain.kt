package com.valid.domain

import com.valid.businessmodels.request.SignInRequest
import com.valid.businessmodels.result.ISignInResult
import com.valid.domain.base.DomainManager
import com.valid.repository.interfaces.ISignInRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

/**
 * Class used to manage the sign in domain for the sign in view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class SignInDomain(private val signInRepository: ISignInRepository) : DomainManager<ISignInResult>() {
    private lateinit var signInResult : ISignInResult
    fun signIn(userName : String, password : String){
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                val singInRequest = SignInRequest( userName = userName, password = password)
                val signIn = signInRepository.localLogIn(signInRequest = singInRequest)
                signInResult.logIn(signIn)
                errorManager.onHideLoader()
            } catch (exception: HttpException) {
                errorManager.onServiceErrorHttpException(exception.message, exception)
            } catch (exception: SocketTimeoutException) {
                errorManager.onSocketTimeoutException(exception.message)
            } catch (exception: IOException) {
                errorManager.onSocketTimeoutException(exception.message)
            } catch (exception: Exception){
                errorManager.onSocketTimeoutException(exception.message)
            }
        }
    }

    override fun domainResult(currentResult: ISignInResult) {
        signInResult = currentResult
    }
}