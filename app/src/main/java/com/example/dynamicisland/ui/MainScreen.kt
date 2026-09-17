package com.example.dynamicisland.ui

import android.content.Intent
import android.provider.Settings
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

@Composable
fun MainScreen(
    vm: SettingsViewModel,
    onGrantOverlay: () -> Unit,
    onGrantNotificationAccess: () -> Unit
) {
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

        PermissionCard(
            title = "Overlay Permission",
            description = if (OverlayPermissionManager.canDrawOverlays(context)) {
                "Granted"
            } else {
                "Required to show the island over other apps"
            },
            action = onGrantOverlay
        )

        PermissionCard(
            title = "Notification Access",
            description = "Required to read supported notifications locally",
            action = onGrantNotificationAccess
        )

        SwitchRow("Enable Island", settings.enabled, vm::setEnabled)

        ElevatedButton(
            onClick = { app.eventBus.post(DynamicIslandEvent.Custom("Test Island", "Custom message")) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Test Island")
        }

        ElevatedButton(
            onClick = { app.eventBus.post(DynamicIslandEvent.Charging(level = 67, charging = true)) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Test Charging")
        }

        ElevatedButton(
            onClick = { app.eventBus.post(DynamicIslandEvent.Notification("Demo App", "Hello", "This is a test")) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Test Notification")
        }

        ElevatedButton(
            onClick = { app.eventBus.post(DynamicIslandEvent.Bluetooth("Galaxy Buds", true)) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Test Bluetooth")
        }
    }
}
