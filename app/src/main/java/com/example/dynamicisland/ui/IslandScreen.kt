package com.example.dynamicisland.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dynamicisland.events.DynamicIslandEvent
import com.example.dynamicisland.ui.components.IslandExpandedCard
import com.example.dynamicisland.ui.components.IslandPill

@Composable
fun IslandScreen(event: DynamicIslandEvent, onDismiss: () -> Unit) {
    val expanded = true
    val height = animateDpAsState(if (expanded) 120.dp else 54.dp, label = "h")
    Surface(
        modifier = Modifier
            .padding(top = 24.dp)
            .width(340.dp)
            .height(height.value)
            .clickable { onDismiss() },
        shape = RoundedCornerShape(32.dp),
        color = Color(0xFF0B0B0F),
        tonalElevation = 6.dp
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AnimatedContent(targetState = event, label = "island") { e ->
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
                        subtitle = "${e.remainingMs / 1000}s left"
                    )
                    is DynamicIslandEvent.Call -> IslandExpandedCard(
                        title = e.name ?: "Call",
                        subtitle = if (e.active) "Active call" else "Calling"
                    )
                    is DynamicIslandEvent.Custom -> IslandExpandedCard(
                        title = e.label,
                        subtitle = "Custom event"
                    )
                }
            }
        }
    }
}
