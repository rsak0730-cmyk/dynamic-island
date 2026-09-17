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
import com.example.dynamicisland.data.SettingsViewModel
import com.example.dynamicisland.overlay.OverlayPermissionManager

@Composable
fun SettingsScreen(vm: SettingsViewModel) {
    val settings by vm.settings.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Dynamic Island", style = MaterialTheme.typography.headlineMedium)

        SwitchRow("Enable Island", settings.enabled) { vm.setEnabled(it) }

        PermissionCard(
            title = "Overlay Permission",
            description = if (OverlayPermissionManager.canDrawOverlays(context)) "Granted" else "Required",
            action = {
                context.startActivity(
                    OverlayPermissionManager.intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        )

        ElevatedButton(onClick = {}) {
            Text("Test Island")
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
