package com.example.dynamicisland.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("dynamic_island_settings")

class SettingsRepository(private val context: Context) {

    private object Keys {
        val ENABLED = booleanPreferencesKey("enabled")
        val AUTO_COLLAPSE = longPreferencesKey("auto_collapse")
        val THEME = stringPreferencesKey("theme")
        val ISLAND_COLOR = longPreferencesKey("island_color")
        val ACCENT_COLOR = longPreferencesKey("accent_color")
        val WIDTH = intPreferencesKey("width")
        val HEIGHT = intPreferencesKey("height")
        val EXPANDED_WIDTH = intPreferencesKey("expanded_width")
        val EXPANDED_HEIGHT = intPreferencesKey("expanded_height")
        val RADIUS = intPreferencesKey("radius")
        val ANIMATION = intPreferencesKey("animation")
        val SHOW_TEXT = booleanPreferencesKey("show_text")
        val SHOW_ICONS = booleanPreferencesKey("show_icons")
        val MUSIC = booleanPreferencesKey("music")
        val NOTIF = booleanPreferencesKey("notif")
        val CALLS = booleanPreferencesKey("calls")
        val CHARGING = booleanPreferencesKey("charging")
        val TIMER = booleanPreferencesKey("timer")
        val STOPWATCH = booleanPreferencesKey("stopwatch")
        val BLUETOOTH = booleanPreferencesKey("bluetooth")
    }

    val settings: Flow<IslandSettings> = context.dataStore.data.map { p ->
        IslandSettings(
            enabled = p[Keys.ENABLED] ?: true,
            autoCollapseMs = p[Keys.AUTO_COLLAPSE] ?: 3500L,
            themeMode = p[Keys.THEME] ?: "system",
            islandColor = p[Keys.ISLAND_COLOR] ?: 0xFF000000,
            accentColor = p[Keys.ACCENT_COLOR] ?: 0xFFFFFFFF,
            widthDp = p[Keys.WIDTH] ?: 180,
            heightDp = p[Keys.HEIGHT] ?: 54,
            expandedWidthDp = p[Keys.EXPANDED_WIDTH] ?: 340,
            expandedHeightDp = p[Keys.EXPANDED_HEIGHT] ?: 130,
            cornerRadiusDp = p[Keys.RADIUS] ?: 28,
            animationMs = p[Keys.ANIMATION] ?: 280,
            showNotificationText = p[Keys.SHOW_TEXT] ?: true,
            showNotificationIcons = p[Keys.SHOW_ICONS] ?: true,
            musicEnabled = p[Keys.MUSIC] ?: true,
            notificationEnabled = p[Keys.NOTIF] ?: true,
            callsEnabled = p[Keys.CALLS] ?: true,
            chargingEnabled = p[Keys.CHARGING] ?: true,
            timerEnabled = p[Keys.TIMER] ?: true,
            stopwatchEnabled = p[Keys.STOPWATCH] ?: true,
            bluetoothEnabled = p[Keys.BLUETOOTH] ?: true
        )
    }

    suspend fun update(block: MutablePreferences.() -> Unit) {
        context.dataStore.edit(block)
    }
}
