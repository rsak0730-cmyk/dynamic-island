package com.example.dynamicisland.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.example.dynamicisland.app.DynamicIslandApp
import com.example.dynamicisland.events.DynamicIslandEvent

class SystemEventReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val app = context.applicationContext as DynamicIslandApp

        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED,
            Intent.ACTION_POWER_DISCONNECTED -> {
                val battery = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
                val level = battery?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
                val charging = intent.action == Intent.ACTION_POWER_CONNECTED
                app.eventBus.post(DynamicIslandEvent.Charging(level = level, charging = charging))
            }

            android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED,
            android.bluetooth.device.action.ACL_CONNECTED,
            android.bluetooth.device.action.ACL_DISCONNECTED -> {
                app.eventBus.post(
                    DynamicIslandEvent.Bluetooth(
                        label = "Bluetooth device",
                        connected = intent.action != android.bluetooth.device.action.ACL_DISCONNECTED
                    )
                )
            }
        }
    }
}
