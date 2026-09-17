package com.example.dynamicisland.timer

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerEngine {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _remaining = MutableStateFlow(0L)
    val remaining: StateFlow<Long> = _remaining.asStateFlow()
    private var job: Job? = null

    fun start(totalMs: Long) {
        job?.cancel()
        _remaining.value = totalMs
        job = scope.launch {
            var left = totalMs
            while (left > 0 && isActive) {
                delay(1000)
                left -= 1000
                _remaining.value = left.coerceAtLeast(0)
            }
        }
    }

    fun cancel() {
        job?.cancel()
        _remaining.value = 0
    }
}
