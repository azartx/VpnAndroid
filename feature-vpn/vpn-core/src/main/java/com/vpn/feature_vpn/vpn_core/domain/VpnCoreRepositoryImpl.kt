package com.vpn.feature_vpn.vpn_core.domain

import com.vpn.feature_vpn.vpn_core.data.convert.VpnCloudConverter
import com.vpn.feature_vpn.vpn_core.data.remote.VpnCoreCloudRest
import com.vpn.feature_vpn.vpn_core_api.model.Data
import com.vpn.feature_vpn.vpn_core_api.repository.VpnCoreRepository

class VpnCoreRepositoryImpl(
    private val vpnCoreCloudRest: VpnCoreCloudRest,
    private val vpnCloudConverter: VpnCloudConverter
) : VpnCoreRepository {

    override suspend fun getData(): Data {
        val result = vpnCoreCloudRest.getData()
        return vpnCloudConverter.convert(result)
    }
}
