package com.vpn.feature_vpn.vpn_core_api.manager

import android.content.Context
import com.vpn.feature_vpn.vpn_core_api.services.IAppVpnService


interface IVpnServiceManager {
    fun startVpn(context: Context)
    fun stopVpn()
    fun restartVpn()
    fun bindService(service: IAppVpnService)
}
