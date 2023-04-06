package com.vpn.feature_vpn.vpn_core.data.remote

import com.vpn.feature_vpn.vpn_core.data.model.DataResponse
import retrofit2.http.GET

interface VpnCoreCloudRest {

    @GET("/weather/latest/by-lat-lng")
    suspend fun getData(
    ): DataResponse
}