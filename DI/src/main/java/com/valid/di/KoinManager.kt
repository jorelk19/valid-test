package com.valid.di

import android.content.Context
import com.valid.repository.RepositoryConfiguration
import org.koin.core.context.startKoin

/**
 * Class used to manage the koin dependency injection that load different modules in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class KoinManager {
    companion object {
        fun initKoin() {
            startKoin {
                modules(
                    arrayListOf(
                        repositoryModule,
                        networkModule,
                        domainModule
                    )
                )
            }
        }

        fun getAppComponent() : AppComponent {
            val appComponent by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { PaymentComponent().appComponent }
            return appComponent
        }

        fun startRepositoryRealm(context: Context){
            RepositoryConfiguration.startRealm(context)
        }
    }
}