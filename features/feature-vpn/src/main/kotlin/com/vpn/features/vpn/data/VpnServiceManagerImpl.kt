package com.vpn.features.vpn.data

import android.content.Context
import android.content.Intent
import android.os.Build
import com.vpn.features.vpn.model.VpnServiceManager
import com.vpn.features.vpn.services.AppVpnService

class VpnServiceManagerImpl : VpnServiceManager {

    private val vpnService: AppVpnService? = null

    override fun startVpn(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(
                Intent(context.applicationContext, AppVpnService::class.java)
            )
        } else {
            context.startService(Intent(context.applicationContext, AppVpnService::class.java))
        }
    }

    override fun stopVpn() {

    }

    override fun restartVpn() {

    }
}
