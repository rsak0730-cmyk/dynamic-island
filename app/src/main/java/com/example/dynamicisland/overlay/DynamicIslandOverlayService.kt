package com.example.dynamicisland.overlay

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.dynamicisland.R

class DynamicIslandOverlayService : Service() {
    override fun onCreate() {
        super.onCreate()
        startForeground(1, makeNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun makeNotification(): Notification {
        val channelId = "overlay_service"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                channelId, "Overlay Service", android.app.NotificationManager.IMPORTANCE_LOW
            )
            (getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager)
                .createNotificationChannel(channel)
        }
        return NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.star_on)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("Dynamic Island running")
            .setOngoing(true)
            .build()
    }
}
