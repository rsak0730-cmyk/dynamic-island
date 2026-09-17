package com.example.dynamicisland.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            // Respect Android 15+ foreground-service restrictions:
            // only start the overlay service if the app is already allowed to do so.
        }
    }
}
