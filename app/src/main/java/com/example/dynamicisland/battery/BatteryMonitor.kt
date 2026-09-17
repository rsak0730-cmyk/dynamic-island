package com.example.dynamicisland.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter

class BatteryMonitor(private val context: Context) {
    fun register(receiver: BroadcastReceiver) {
        context.registerReceiver(receiver, IntentFilter(android.content.Intent.ACTION_BATTERY_CHANGED))
    }
}
