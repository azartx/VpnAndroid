package com.vpn.feature_vpn.vpn_core.di

import com.vpn.core.di.FeatureImpl
import com.vpn.core.di.dsl.ImplDSL
import com.vpn.core.di.resolve
import com.vpn.feature_vpn.vpn_core.data.convert.VpnCloudConverter
import com.vpn.feature_vpn.vpn_core.data.remote.VpnCoreCloudRest
import com.vpn.feature_vpn.vpn_core.domain.VpnServiceManagerImpl
import com.vpn.feature_vpn.vpn_core.notifications.VpnNotification
import com.vpn.feature_vpn.vpn_core_api.di.VpnCoreFeatureApi
import com.vpn.feature_vpn.vpn_core_api.manager.IVpnServiceManager
import retrofit2.Retrofit
import retrofit2.create

object VpnCoreFeatureImpl : FeatureImpl(VpnCoreFeatureApi) {

    override fun ImplDSL.definitions() {
        factory { resolve(::VpnCloudConverter) }

        factory<VpnCoreCloudRest> { get<Retrofit>().create() }

        //scoped<VpnCoreRepository> { resolve(::VpnCoreRepositoryImpl) }

        scoped<IVpnServiceManager> { resolve(::VpnServiceManagerImpl) }
        scoped { resolve(::VpnNotification) }
    }
}