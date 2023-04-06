package com.vpn.feature_vpn.vpn_core.domain

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.vpn.feature_vpn.vpn_core.notifications.VpnNotification
import com.vpn.feature_vpn.vpn_core.services.AppVpnService
import com.vpn.feature_vpn.vpn_core_api.manager.IVpnServiceManager
import com.vpn.feature_vpn.vpn_core_api.services.IAppVpnService
import com.vpn.feature_vpn.vpn_util.Constants.VPN_NOTIFICATION_ID
import java.lang.ref.SoftReference

class VpnServiceManagerImpl(private val vpnNotification: VpnNotification) : IVpnServiceManager {

    private var vpnService: SoftReference<AppVpnService>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun startVpn(context: Context) {
        context.startForegroundService(Intent(context.applicationContext, IAppVpnService::class.java))
        val notification = vpnNotification.create()
        vpnService?.get()?.startForeground(VPN_NOTIFICATION_ID, notification) ?: throw NullPointerException("VPN service is null") // todo
    }

    override fun stopVpn() {

    }

    override fun restartVpn() {

    }

    override fun bindService(service: IAppVpnService) {
        vpnService = SoftReference(service as AppVpnService)
    }
}
