package com.vpn.features.vpn.data

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.vpn.features.vpn.model.VpnServiceManager
import com.vpn.features.vpn.notifications.VpnNotification
import com.vpn.features.vpn.services.AppVpnService
import com.vpn.features.vpn.utils.Constants.VPN_NOTIFICATION_ID
import java.lang.ref.SoftReference

class VpnServiceManagerImpl(private val vpnNotification: VpnNotification) : VpnServiceManager {

    private var vpnService: SoftReference<AppVpnService>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun startVpn(context: Context) {
        context.startForegroundService(Intent(context.applicationContext, AppVpnService::class.java))
        val notification = vpnNotification.create()
        vpnService?.get()?.startForeground(VPN_NOTIFICATION_ID, notification) ?: throw NullPointerException("VPN service is null") // todo
    }

    override fun stopVpn() {

    }

    override fun restartVpn() {

    }

    override fun bindService(service: AppVpnService) {
        vpnService = SoftReference(service)
    }
}
