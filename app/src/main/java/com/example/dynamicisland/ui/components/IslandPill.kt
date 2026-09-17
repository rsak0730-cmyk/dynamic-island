package com.example.dynamicisland.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IslandPill(label: String = "● Dynamic Island") {
    Box(
        Modifier
            .padding(8.dp)
            .background(Color.Black, RoundedCornerShape(999.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(label, color = Color.White)
    }
}
