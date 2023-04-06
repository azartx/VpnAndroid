package com.vpn.features_vpn.vpn.services

import android.content.Intent
import android.net.VpnService

internal class AppVpnService : VpnService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}
