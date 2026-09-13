package com.custom.dynamicisland

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class IslandNotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.notification?.extras?.let { extras ->
            val title = extras.getString("android.title") ?: ""
            val text = extras.getCharSequence("android.text")?.toString() ?: ""
            
            // Only capture actual messages, ignore silent background system updates
            if (title.isNotEmpty() || text.isNotEmpty()) {
                val intent = Intent("com.custom.dynamicisland.NOTIFICATION")
                intent.putExtra("title", title)
                intent.putExtra("text", text)
                sendBroadcast(intent)
            }
        }
    }
}

