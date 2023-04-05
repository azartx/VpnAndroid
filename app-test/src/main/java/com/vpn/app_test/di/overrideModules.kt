package com.vpn.app_test.di

import com.vpn.core.network_api.NetworkLibraryApi
import io.mockk.mockk
import okhttp3.OkHttpClient
import org.koin.dsl.module

internal val overrideModules = listOf(
    module {
        scope<NetworkLibraryApi> {
            scoped<OkHttpClient> { mockk() }
        }
    }
)
