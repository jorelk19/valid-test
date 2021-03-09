package com.valid.di

import com.valid.businessmodels.api.PaymentApi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * CLass used to manage the network access from apis
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 **/

const val okHttpClientWithoutInterceptor = "defaultOkHttpClient"
const val retrofitPayment = "retrofitPayment"
private const val DEFAULT_TIME_OUT = 60L


/**
 * Initialize the module withe the respective retrofit information
 * */
val networkModule = module {

    // Retrofit and OkHttpClient instances
    single(named(okHttpClientWithoutInterceptor)) { provideDefaultOkHttpClient() }
    single(named(retrofitPayment)) { provideRetrofitPaymentClient(get(named(okHttpClientWithoutInterceptor))) }
    // API
    single { providePaymentApi(get(named(retrofitPayment))) }
}


/**
 * OkHttpClient without Interceptor
 */
private fun provideDefaultOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .build()
}

/**
 * Retrofit movie clients
 */
private fun provideRetrofitPaymentClient(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.PAYMENT_API)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

/**
 * Movie api provider
 * */
private fun providePaymentApi(retrofit: Retrofit): PaymentApi = retrofit.create(PaymentApi::class.java)

