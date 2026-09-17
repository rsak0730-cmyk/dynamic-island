package com.example.dynamicisland.events

sealed class DynamicIslandEvent(open val priority: Int) {
    data class Music(
        val title: String? = null,
        val artist: String? = null,
        val isPlaying: Boolean = false,
        val positionMs: Long = 0L,
        val durationMs: Long = 0L,
        val artworkKey: String? = null,
        override val priority: Int = 50
    ) : DynamicIslandEvent(priority)

    data class Notification(
        val appName: String? = null,
        val title: String? = null,
        val text: String? = null,
        val smallIconKey: String? = null,
        override val priority: Int = 20
    ) : DynamicIslandEvent(priority)

    data class Charging(
        val level: Int,
        val charging: Boolean,
        val pluggedType: String? = null,
        override val priority: Int = 30
    ) : DynamicIslandEvent(priority)

    data class Timer(
        val remainingMs: Long,
        val running: Boolean,
        val label: String? = null,
        override val priority: Int = 40
    ) : DynamicIslandEvent(priority)

    data class Stopwatch(
        val elapsedMs: Long,
        val running: Boolean,
        override val priority: Int = 35
    ) : DynamicIslandEvent(priority)

    data class Call(
        val name: String? = null,
        val active: Boolean = false,
        val incoming: Boolean = false,
        override val priority: Int = 100
    ) : DynamicIslandEvent(priority)

    data class Bluetooth(
        val label: String,
        val connected: Boolean,
        override val priority: Int = 15
    ) : DynamicIslandEvent(priority)

    data class Custom(
        val label: String,
        val subtitle: String? = null,
        override val priority: Int = 10
    ) : DynamicIslandEvent(priority)
}
