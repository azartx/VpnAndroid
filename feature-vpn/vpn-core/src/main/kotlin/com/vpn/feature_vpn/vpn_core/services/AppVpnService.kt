package com.vpn.feature_vpn.vpn_core.services

import android.content.Intent
import android.net.VpnService
import com.vpn.feature_vpn.vpn_core_api.services.IAppVpnService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AppVpnService : VpnService(), IAppVpnService, KoinComponent {
    override fun onCreate() {
        super.onCreate()
        val serviceManager = get<com.vpn.feature_vpn.vpn_core_api.manager.IVpnServiceManager>()
        serviceManager.bindService(this)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}
