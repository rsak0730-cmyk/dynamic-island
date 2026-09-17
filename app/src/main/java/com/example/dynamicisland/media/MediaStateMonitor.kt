package com.example.dynamicisland.media

import android.content.Context
import android.media.session.MediaSessionManager

class MediaStateMonitor(context: Context) {
    private val msm = context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
}
