package com.vpn.core.network_api

import com.vpn.core.di.LibraryApi
import com.vpn.core.di.dsl.ApiDSL
import retrofit2.Retrofit

object NetworkLibraryApi : LibraryApi() {

    override fun ApiDSL.definitions() {
        scoped<Retrofit>()
    }
}