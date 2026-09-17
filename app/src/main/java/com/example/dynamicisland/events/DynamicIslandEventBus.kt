package com.example.dynamicisland.events

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DynamicIslandEventBus {
    private val _current = MutableStateFlow<DynamicIslandEvent?>(null)
    val current: StateFlow<DynamicIslandEvent?> = _current.asStateFlow()

    fun post(event: DynamicIslandEvent) {
        val cur = _current.value
        if (cur == null || event.priority >= cur.priority) {
            _current.value = event
        }
    }

    fun clear() {
        _current.value = null
    }

    fun replace(event: DynamicIslandEvent?) {
        _current.value = event
    }
}
