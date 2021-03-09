package com.valid.domain

import com.valid.businessmodels.result.ISplashScreenResult
import com.valid.domain.base.DomainManager
import com.valid.repository.interfaces.IDefaultUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class SplashScreenDomain(private val defaultUserRepository: IDefaultUserRepository) : DomainManager<ISplashScreenResult>() {
    private lateinit var currentSplashScreenResult: ISplashScreenResult
    override fun domainResult(splashScreenResult: ISplashScreenResult) {
        currentSplashScreenResult = splashScreenResult
    }

    fun createDefaultUser() {
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                defaultUserRepository.saveDefaultUser()
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