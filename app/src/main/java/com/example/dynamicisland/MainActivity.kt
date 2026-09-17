package com.example.dynamicisland

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dynamicisland.data.SettingsViewModel
import com.example.dynamicisland.overlay.OverlayPermissionManager
import com.example.dynamicisland.ui.MainScreen
import com.example.dynamicisland.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                val vm: SettingsViewModel = viewModel()
                MainScreen(
                    vm = vm,
                    onGrantOverlay = {
                        startActivity(
                            OverlayPermissionManager.intent()
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    },
                    onGrantNotificationAccess = {
                        startActivity(
                            Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    }
                )
            }
        }
    }
}
