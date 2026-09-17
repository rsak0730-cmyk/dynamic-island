package com.example.dynamicisland.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicisland.app.DynamicIslandApp
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = (app as DynamicIslandApp).settingsRepository
    val settings: StateFlow<IslandSettings> = repo.settings.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), IslandSettings()
    )

    fun setEnabled(enabled: Boolean) = viewModelScope.launch {
        repo.update { this[booleanPreferencesKey("enabled")] = enabled }
    }

    fun setAutoCollapse(ms: Long) = viewModelScope.launch {
        repo.update { this[longPreferencesKey("auto_collapse")] = ms }
    }
}
