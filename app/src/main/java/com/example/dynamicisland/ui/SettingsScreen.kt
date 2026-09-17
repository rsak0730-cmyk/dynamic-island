package com.example.dynamicisland.ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dynamicisland.app.DynamicIslandApp
import com.example.dynamicisland.data.SettingsViewModel
import com.example.dynamicisland.events.DynamicIslandEvent
import com.example.dynamicisland.overlay.OverlayPermissionManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue

@Composable
fun SettingsScreen(vm: SettingsViewModel = viewModel()) {
    val settings by vm.settings.collectAsState()
    val context = LocalContext.current
    val app = context.applicationContext as DynamicIslandApp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Dynamic Island", style = MaterialTheme.typography.headlineMedium)

        SwitchRow("Enable Island", settings.enabled, vm::setEnabled)

        PermissionCard(
            title = "Overlay Permission",
            description = if (OverlayPermissionManager.canDrawOverlays(context)) "Granted" else "Required",
            action = {
                context.startActivity(
                    OverlayPermissionManager.intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        )

        PermissionCard(
            title = "Notification Access",
            description = "Required to show notifications in the island",
            action = {
                context.startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
            }
        )

        ElevatedButton(onClick = {
            app.eventBus.post(
                DynamicIslandEvent.Custom("Test Island", "This is a custom test event")
            )
        }) {
            Text("Test Island")
        }

        ElevatedButton(onClick = {
            app.eventBus.post(DynamicIslandEvent.Charging(level = 67, charging = true))
        }) {
            Text("Test Charging")
        }

        ElevatedButton(onClick = {
            app.eventBus.post(DynamicIslandEvent.Bluetooth("Galaxy Buds", true))
        }) {
            Text("Test Bluetooth")
        }
    }
}

@Composable
private fun SwitchRow(title: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(title)
        Switch(checked = checked, onCheckedChange = onChange)
    }
}
