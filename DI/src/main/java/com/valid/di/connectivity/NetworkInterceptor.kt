package com.valid.di.connectivity

import com.valid.di.BuildConfig
import okhttp3.*
import org.json.JSONException
import java.io.IOException
import java.net.SocketTimeoutException

class NetworkInterceptor() : Interceptor {

    companion object {
        const val TIME_OUT_EXCEPTION_MESSAGE = "Timeout exception"
    }

    @Throws(IOException::class, SocketTimeoutException::class)
    override fun intercept(chain: Interceptor.Chain): Response  {
            try {
                var request = chain.request()
                request = addDynamicHeadersToRequest(request)
                return chain.proceed(request)
            } catch (e: SocketTimeoutException) {
                throw SocketTimeoutException(TIME_OUT_EXCEPTION_MESSAGE)
            } catch (e: JSONException) {
                throw SocketTimeoutException(TIME_OUT_EXCEPTION_MESSAGE)
            }
    }

    private fun addDynamicHeadersToRequest(request: Request): Request {
        val originalHttpUrl: HttpUrl = request.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.APY_KEY)
            .addQueryParameter("format", BuildConfig.FORMAT)
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = request.newBuilder()
            .url(url)

        return  requestBuilder.build()
    }
}