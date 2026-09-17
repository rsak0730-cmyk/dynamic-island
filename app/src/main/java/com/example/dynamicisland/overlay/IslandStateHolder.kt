package com.example.dynamicisland.overlay

import com.example.dynamicisland.events.DynamicIslandEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IslandStateHolder {
    private val _event = MutableStateFlow<DynamicIslandEvent?>(null)
    val event: StateFlow<DynamicIslandEvent?> = _event
    var expanded: Boolean = false
        private set

    fun show(event: DynamicIslandEvent) {
        _event.value = event
        expanded = true
    }

    fun collapse() {
        expanded = false
    }

    fun clear() {
        _event.value = null
        expanded = false
    }
}
