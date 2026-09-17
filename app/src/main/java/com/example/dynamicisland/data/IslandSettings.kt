package com.example.dynamicisland.data

data class IslandSettings(
    val enabled: Boolean = true,
    val autoCollapseMs: Long = 3500,
    val themeMode: String = "system",
    val islandColor: Long = 0xFF000000,
    val accentColor: Long = 0xFFFFFFFF,
    val widthDp: Int = 180,
    val heightDp: Int = 54,
    val expandedWidthDp: Int = 320,
    val expandedHeightDp: Int = 120,
    val cornerRadiusDp: Int = 28,
    val animationMs: Int = 280,
    val showNotificationText: Boolean = true,
    val showNotificationIcons: Boolean = true,
    val musicEnabled: Boolean = true,
    val notificationEnabled: Boolean = true,
    val callsEnabled: Boolean = true,
    val chargingEnabled: Boolean = true,
    val timerEnabled: Boolean = true,
    val bluetoothEnabled: Boolean = true
)
