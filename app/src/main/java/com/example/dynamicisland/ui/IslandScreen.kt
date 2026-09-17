package com.example.dynamicisland.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dynamicisland.events.DynamicIslandEvent
import com.example.dynamicisland.ui.components.IslandExpandedCard
import com.example.dynamicisland.util.FormatUtils

@Composable
fun IslandScreen(event: DynamicIslandEvent, onDismiss: () -> Unit) {
    val height = animateDpAsState(targetValue = 132.dp, label = "height")

    Surface(
        modifier = Modifier
            .padding(top = 24.dp)
            .width(340.dp)
            .height(height.value)
            .clickable { onDismiss() },
        shape = RoundedCornerShape(32.dp),
        color = Color(0xFF09090D),
        tonalElevation = 6.dp
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AnimatedContent(targetState = event, label = "dynamic_island") { e ->
                when (e) {
                    is DynamicIslandEvent.Notification -> IslandExpandedCard(
                        title = e.title ?: "Notification",
                        subtitle = e.text ?: e.appName.orEmpty()
                    )
                    is DynamicIslandEvent.Music -> IslandExpandedCard(
                        title = e.title ?: "Music",
                        subtitle = e.artist ?: "Unknown artist"
                    )
                    is DynamicIslandEvent.Charging -> IslandExpandedCard(
                        title = "Charging ${e.level}%",
                        subtitle = if (e.charging) "Charging" else "Not charging"
                    )
                    is DynamicIslandEvent.Timer -> IslandExpandedCard(
                        title = "Timer",
                        subtitle = FormatUtils.formatMs(e.remainingMs)
                    )
                    is DynamicIslandEvent.Stopwatch -> IslandExpandedCard(
                        title = "Stopwatch",
                        subtitle = FormatUtils.formatMs(e.elapsedMs)
                    )
                    is DynamicIslandEvent.Call -> IslandExpandedCard(
                        title = e.name ?: "Call",
                        subtitle = if (e.active) "Active call" else "Incoming call"
                    )
                    is DynamicIslandEvent.Bluetooth -> IslandExpandedCard(
                        title = e.label,
                        subtitle = if (e.connected) "Connected" else "Disconnected"
                    )
                    is DynamicIslandEvent.Custom -> IslandExpandedCard(
                        title = e.label,
                        subtitle = e.subtitle ?: "Custom event"
                    )
                }
            }
        }
    }
}
