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
    private var autoDismissJob: Job? = null

    fun start() {
        if (started) return
        started = true

        scope.launch {
            eventBus.current.collectLatest { event ->
                autoDismissJob?.cancel()

                if (event != null) {
                    windowManager.show(event, onDismiss = { eventBus.clear() })

                    autoDismissJob = launch {
                        val delayMs = settingsRepository.settings
                        // We do not collect settings here to avoid keeping the overlay hot.
                        delay(3500L)
                        eventBus.clear()
                    }
                } else {
                    windowManager.remove()
                }
            }
        }
    }

    fun stop() {
        autoDismissJob?.cancel()
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
