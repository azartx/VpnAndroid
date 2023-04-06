package com.vpn.features.vpn.services

import android.content.Intent
import android.net.VpnService
import com.vpn.features.vpn.model.VpnServiceManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AppVpnService : VpnService(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        val serviceManager = get<VpnServiceManager>()
        serviceManager.bindService(this)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}
