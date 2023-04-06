package com.vpn.feature_vpn.vpn_core_api.repository

import com.vpn.feature_vpn.vpn_core_api.model.Data

interface VpnCoreRepository {

    suspend fun getData(): Data
}