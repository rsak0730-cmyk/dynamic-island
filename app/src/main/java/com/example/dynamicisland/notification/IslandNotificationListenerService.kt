package com.example.dynamicisland.notification

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.example.dynamicisland.app.DynamicIslandApp
import com.example.dynamicisland.events.DynamicIslandEvent

class IslandNotificationListenerService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val app = application as DynamicIslandApp
        val n = sbn.notification
        val title = n.extras.getCharSequence("android.title")?.toString()
        val text = n.extras.getCharSequence("android.text")?.toString()
        app.eventBus.post(
            DynamicIslandEvent.Notification(
                appName = sbn.packageName,
                title = title,
                text = text
            )
        )
    }
}
