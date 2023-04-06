package com.vpn.features.vpn.model

import android.content.Context
import com.vpn.features.vpn.services.AppVpnService

interface VpnServiceManager {
    fun startVpn(context: Context)
    fun stopVpn()
    fun restartVpn()
    fun bindService(service: AppVpnService)
}
