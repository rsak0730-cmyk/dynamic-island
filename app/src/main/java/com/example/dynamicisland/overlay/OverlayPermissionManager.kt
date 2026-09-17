package com.example.dynamicisland.overlay

import android.content.Context
import android.content.Intent
import android.provider.Settings

object OverlayPermissionManager {
    fun canDrawOverlays(context: Context): Boolean = Settings.canDrawOverlays(context)

    fun intent(): Intent = Intent(
        Settings.ACTION_MANAGE_OVERLAY_PERMISSION
    )
}
