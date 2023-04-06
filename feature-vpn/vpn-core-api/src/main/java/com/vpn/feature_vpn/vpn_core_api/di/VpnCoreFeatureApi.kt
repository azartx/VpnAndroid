package com.vpn.feature_vpn.vpn_core_api.di

import com.vpn.core.di.FeatureApi
import com.vpn.core.di.dsl.ApiDSL
import com.vpn.feature_vpn.vpn_core_api.repository.VpnCoreRepository

object VpnCoreFeatureApi : FeatureApi() {

    override fun ApiDSL.definitions() {
        scoped<VpnCoreRepository>()
    }
}