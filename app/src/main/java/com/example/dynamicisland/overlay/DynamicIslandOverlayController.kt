package com.example.dynamicisland.overlay

import android.content.Context
import com.example.dynamicisland.data.SettingsRepository
import com.example.dynamicisland.events.DynamicIslandEvent
import com.example.dynamicisland.events.DynamicIslandEventBus
import com.example.dynamicisland.timer.TimerEngine
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

class DynamicIslandOverlayController(
    private val context: Context,
    private val settingsRepository: SettingsRepository,
    private val eventBus: DynamicIslandEventBus,
    private val timerEngine: TimerEngine
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val windowManager = IslandWindowManager(context)
    private var started = false

    fun start() {
        if (started) return
        started = true
        scope.launch {
            eventBus.current.collectLatest { event ->
                if (event != null) {
                    windowManager.show(event, onDismiss = { eventBus.clear() })
                    launch {
                        val delayMs = settingsRepository.settings
                        delay(250)
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

    fun post(event: DynamicIslandEvent) {
        eventBus.post(event)
    }

    fun clear() {
        eventBus.clear()
    }
}
