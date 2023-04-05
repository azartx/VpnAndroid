package com.vpn.features.vpn.model

import android.content.Context

interface VpnServiceManager {
    fun startVpn(context: Context)
    fun stopVpn()
    fun restartVpn()
}
