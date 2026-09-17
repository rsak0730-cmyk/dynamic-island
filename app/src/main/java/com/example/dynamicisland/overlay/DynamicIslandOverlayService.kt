package com.example.dynamicisland.overlay

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY
    override fun onBind(intent: Intent?): IBinder? = null

    private fun makeNotification(): Notification {
        val channelId = "overlay_service"
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(channelId, "Overlay Service", NotificationManager.IMPORTANCE_LOW)
            )
        }
        return NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.star_on)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("Dynamic Island running")
            .setOngoing(true)
            .setSilent(true)
            .build()
    }
}
