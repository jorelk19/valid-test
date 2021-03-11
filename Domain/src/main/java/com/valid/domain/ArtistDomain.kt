package com.valid.domain

import com.valid.businessmodels.business.Country
import com.valid.businessmodels.result.IArtistResult
import com.valid.domain.base.DomainManager
import com.valid.repository.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class ArtistDomain(private val repository: RemoteRepository) : DomainManager<IArtistResult>() {
    private lateinit var currentArtistResult : IArtistResult
    override fun domainResult(artistResult: IArtistResult) {
        currentArtistResult = artistResult
    }

    fun searchArtist(country : Country){
        launch (Dispatchers.Main){
            try {
                errorManager.onShowLoader()
                val response = withContext(Dispatchers.IO){ repository.getArtists(country.countryName.toLowerCase()) }
                currentArtistResult.setArtistResult(response.topArtists)
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