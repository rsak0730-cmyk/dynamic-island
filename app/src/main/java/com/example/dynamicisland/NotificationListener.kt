package com.example.dynamicisland

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class NotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val extras = sbn?.notification?.extras ?: return
        val title = extras.getString("android.title") ?: "New Alert"
        val text = extras.getCharSequence("android.text")?.toString() ?: ""

        // Broadcasts the incoming message data to your floating island
        val intent = Intent("UPDATE_ISLAND")
        intent.putExtra("title", title)
        intent.putExtra("text", text)
        sendBroadcast(intent)
    }
}

