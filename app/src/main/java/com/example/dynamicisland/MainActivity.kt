package com.example.dynamicisland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dynamicisland.data.SettingsViewModel
import com.example.dynamicisland.ui.SettingsScreen
import com.example.dynamicisland.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val vm: SettingsViewModel = viewModel()
                SettingsScreen(vm = vm)
            }
        }
    }
}
