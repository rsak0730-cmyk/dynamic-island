package com.example.dynamicisland.util

object FormatUtils {
    fun formatMs(ms: Long): String {
        val sec = (ms / 1000).coerceAtLeast(0)
        val m = sec / 60
        val s = sec % 60
        return "%02d:%02d".format(m, s)
    }
}
