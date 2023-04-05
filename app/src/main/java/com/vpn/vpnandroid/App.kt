package com.vpn.vpnandroid

import android.app.Application
import com.vpn.core.di.FeatureApiManager
import com.vpn.core.di.FeatureImpl
import com.vpn.core.network.NetworkLibraryImpl
import com.vpn.vpnandroid.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    mainModule,
                    module {
                        single(FeatureApiManager.FEATURE_MAP_QUALIFIER) { features().associateBy { it.qualifier } }
                    }
                )
            )
        }
    }
}

fun features(): List<FeatureImpl> =
    listOf(
//        SchedulersLibraryImpl,
        NetworkLibraryImpl,
//        GithubCoreFeatureImpl,
//        GithubUserListFeatureImpl,
//        GithubUserDetailsFeatureImpl,
//
//        WeatherCoreFeatureImpl,
//        WeatherFeatureImpl,
//        WeatherNetworkLibraryImpl,
//        ForecastWeatherFeatureImpl,
//        HistoryWeatherFeatureImpl,
//        LatestWeatherFeatureImpl
    )