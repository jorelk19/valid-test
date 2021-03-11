package com.valid.domain

import com.valid.businessmodels.business.Country
import com.valid.businessmodels.result.ITrackResult
import com.valid.domain.base.DomainManager
import com.valid.repository.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class TrackDomain(private val repository: RemoteRepository) : DomainManager<ITrackResult>() {
    private lateinit var currentTrackResult : ITrackResult
    override fun domainResult(trackResult: ITrackResult) {
        currentTrackResult = trackResult
    }

    fun searchTrack(trackSearched : String, country : Country){
        launch (Dispatchers.Main){
            try {
                errorManager.onShowLoader()
                val response = withContext(Dispatchers.IO){ repository.getTracks(country.countryName.toLowerCase()) }
                currentTrackResult.setTrackResult(response.topArtists)
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