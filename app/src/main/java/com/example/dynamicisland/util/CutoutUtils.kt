package com.example.dynamicisland.util

import android.app.Activity
import android.view.WindowInsets

object CutoutUtils {
    fun topInset(activity: Activity): Int {
        val insets = activity.window.decorView.rootWindowInsets
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            insets?.getInsets(WindowInsets.Type.displayCutout())?.top ?: 0
        } else 0
    }
}
