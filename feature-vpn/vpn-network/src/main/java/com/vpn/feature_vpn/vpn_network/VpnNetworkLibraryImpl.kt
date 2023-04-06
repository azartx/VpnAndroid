package com.vpn.feature_vpn.vpn_network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vpn.core.di.LibraryImpl
import com.vpn.core.di.dsl.ImplDSL
import com.vpn.feature_vpn.vpn_network_api.VpnNetworkLibraryApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object VpnNetworkLibraryImpl : LibraryImpl(VpnNetworkLibraryApi) {

    override fun ImplDSL.definitions() {

        factory {
            HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        scoped {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }
        scoped {
            OkHttpClient.Builder()
                .addNetworkInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        scoped {
            Retrofit.Builder()
                .client(get())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .baseUrl("YOUR BASE URL")
                .build()
        }
    }
}