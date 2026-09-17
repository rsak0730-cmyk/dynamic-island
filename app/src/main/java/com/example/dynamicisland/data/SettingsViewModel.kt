package com.example.dynamicisland.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicisland.app.DynamicIslandApp
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = (app as DynamicIslandApp).settingsRepository

    val settings: StateFlow<IslandSettings> = repo.settings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        IslandSettings()
    )

    fun setEnabled(enabled: Boolean) = viewModelScope.launch {
        repo.update { this[booleanPreferencesKey("enabled")] = enabled }
    }

    fun setAutoCollapse(ms: Long) = viewModelScope.launch {
        repo.update { this[longPreferencesKey("auto_collapse")] = ms }
    }

    fun setTheme(theme: String) = viewModelScope.launch {
        repo.update { this[stringPreferencesKey("theme")] = theme }
    }

    fun setEventEnabled(key: String, enabled: Boolean) = viewModelScope.launch {
        repo.update {
            when (key) {
                "music" -> this[booleanPreferencesKey("music")] = enabled
                "notif" -> this[booleanPreferencesKey("notif")] = enabled
                "calls" -> this[booleanPreferencesKey("calls")] = enabled
                "charging" -> this[booleanPreferencesKey("charging")] = enabled
                "timer" -> this[booleanPreferencesKey("timer")] = enabled
                "stopwatch" -> this[booleanPreferencesKey("stopwatch")] = enabled
                "bluetooth" -> this[booleanPreferencesKey("bluetooth")] = enabled
            }
        }
    }

    fun testEnabled() = viewModelScope.launch {
        repo.update { this[booleanPreferencesKey("enabled")] = true }
    }
}
