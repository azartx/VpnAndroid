package com.vpn.feature_vpn.vpn_core.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.vpn.feature_vpn.vpn_util.Constants.VPN_NOTIFICATION_CHANNEL_ID
import com.vpn.feature_vpn.vpn_util.Constants.VPN_NOTIFICATION_CHANNEL_NAME
import com.vpn.features.vpn.R

class VpnNotification(private val context: Context) {
    private val manager: NotificationManager by lazy {
        context.getSystemService(NotificationManager::class.java)
    }

    private var notification: Notification? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun create(): Notification {
        createNotificationChannelIfNeed()
        return notification ?: Notification.Builder(context, VPN_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_icon_background) // todo
            .setContentTitle(context.getString(R.string.vpn_notification_title)) // todo
            .setOngoing(true)
            .build().apply {
                notification = this
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannelIfNeed() {
        val hasChannel = manager.notificationChannels
            .firstOrNull { it.id == VPN_NOTIFICATION_CHANNEL_ID } != null
        if (!hasChannel) {
            manager.createNotificationChannel(
                NotificationChannel(
                    VPN_NOTIFICATION_CHANNEL_ID,
                    VPN_NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
    }
}
