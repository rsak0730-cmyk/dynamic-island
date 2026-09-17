package com.example.dynamicisland.events

sealed class DynamicIslandEvent(open val priority: Int) {
    data class Music(
        val title: String?,
        val artist: String?,
        val playing: Boolean,
        override val priority: Int = 50
    ) : DynamicIslandEvent(priority)

    data class Notification(
        val appName: String?,
        val title: String?,
        val text: String?,
        override val priority: Int = 20
    ) : DynamicIslandEvent(priority)

    data class Charging(
        val level: Int,
        val charging: Boolean,
        override val priority: Int = 30
    ) : DynamicIslandEvent(priority)

    data class Timer(
        val remainingMs: Long,
        val running: Boolean,
        override val priority: Int = 40
    ) : DynamicIslandEvent(priority)

    data class Call(
        val name: String?,
        val active: Boolean,
        override val priority: Int = 100
    ) : DynamicIslandEvent(priority)

    data class Custom(
        val label: String,
        override val priority: Int = 10
    ) : DynamicIslandEvent(priority)
}
