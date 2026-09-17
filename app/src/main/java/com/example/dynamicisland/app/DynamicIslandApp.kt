package com.example.dynamicisland.app

import android.app.Application
import com.example.dynamicisland.data.SettingsRepository
import com.example.dynamicisland.events.DynamicIslandEventBus
import com.example.dynamicisland.overlay.DynamicIslandOverlayController
import com.example.dynamicisland.timer.TimerEngine

class DynamicIslandApp : Application() {
    lateinit var settingsRepository: SettingsRepository
        private set

    lateinit var eventBus: DynamicIslandEventBus
        private set

    lateinit var overlayController: DynamicIslandOverlayController
        private set

    lateinit var timerEngine: TimerEngine
        private set

    override fun onCreate() {
        super.onCreate()
        settingsRepository = SettingsRepository(this)
        eventBus = DynamicIslandEventBus()
        timerEngine = TimerEngine()
        overlayController = DynamicIslandOverlayController(this, settingsRepository, eventBus, timerEngine)
        overlayController.start()
    }
}
