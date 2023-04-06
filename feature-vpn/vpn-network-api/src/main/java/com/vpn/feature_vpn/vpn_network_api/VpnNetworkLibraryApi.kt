package com.vpn.feature_vpn.vpn_network_api

import com.vpn.core.di.LibraryApi
import com.vpn.core.di.dsl.ApiDSL
import retrofit2.Retrofit

object VpnNetworkLibraryApi : LibraryApi() {

    override fun ApiDSL.definitions() {
        scoped<Retrofit>()
    }
}