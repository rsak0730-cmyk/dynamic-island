package com.example.dynamicisland.overlay

import android.content.Context
import com.example.dynamicisland.data.SettingsRepository
import com.example.dynamicisland.events.DynamicIslandEventBus
import com.example.dynamicisland.ui.IslandScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DynamicIslandOverlayController(
    private val context: Context,
    private val settingsRepository: SettingsRepository,
    private val eventBus: DynamicIslandEventBus
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val windowManager = IslandWindowManager(context)
    private var started = false

    fun start() {
        if (started) return
        started = true
        scope.launch {
            eventBus.current.collect { event ->
                if (event != null) {
                    windowManager.show {
                        IslandScreen(event = event, onDismiss = { eventBus.clear() })
                    }
                } else {
                    windowManager.remove()
                }
            }
        }
    }

    fun stop() {
        windowManager.remove()
        started = false
    }
}
