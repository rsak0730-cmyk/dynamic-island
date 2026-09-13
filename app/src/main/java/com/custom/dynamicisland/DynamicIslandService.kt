package com.custom.dynamicisland

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Service
import android.content.ClipDescription
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.*
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast

class DynamicIslandService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View
    private lateinit var params: WindowManager.LayoutParams

    private lateinit var compactView: LinearLayout
    private lateinit var expandedMusicView: RelativeLayout
    private lateinit var dragDropView: LinearLayout

    private var isExpanded = false
    private val compactWidth = 350
    private val compactHeight = 110

    override fun onBind(intent: Intent?): IBinder? = null

    @SuppressLint("ClickableViewAccessibility", "InflateParams")
    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        floatingView = LayoutInflater.from(this).inflate(R.layout.layout_dynamic_island, null)

        compactView = floatingView.findViewById(R.id.compactView)
        expandedMusicView = floatingView.findViewById(R.id.expandedMusicView)
        dragDropView = floatingView.findViewById(R.id.dragDropView)

        val layoutFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        params = WindowManager.LayoutParams(
            compactWidth,
            compactHeight,
            layoutFlag,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            y = 30
        }

        windowManager.addView(floatingView, params)
        setupGestures()
        setupVivoDragAndGo()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupGestures() {
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                if (!isExpanded) {
                    animateIsland(compactWidth, 800, compactHeight, 250, expandedMusicView)
                    isExpanded = true
                } else {
                    animateIsland(800, compactWidth, 250, compactHeight, compactView)
                    isExpanded = false
                }
                return true
            }
        })

        floatingView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private fun setupVivoDragAndGo() {
        floatingView.setOnDragListener { _, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                DragEvent.ACTION_DRAG_ENTERED -> {
                    animateIsland(params.width, 600, params.height, 180, dragDropView)
                    true
                }
                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    Toast.makeText(this, "Dropped: ${item.text}", Toast.LENGTH_LONG).show()
                    animateIsland(600, compactWidth, 180, compactHeight, compactView)
                    true
                }
                DragEvent.ACTION_DRAG_EXITED, DragEvent.ACTION_DRAG_ENDED -> {
                    if (params.width != compactWidth) {
                        animateIsland(params.width, compactWidth, params.height, compactHeight, compactView)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun animateIsland(startW: Int, endW: Int, startH: Int, endH: Int, targetView: View) {
        compactView.visibility = View.GONE
        expandedMusicView.visibility = View.GONE
        dragDropView.visibility = View.GONE
        targetView.visibility = View.VISIBLE

        val anim = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 450
            interpolator = OvershootInterpolator(1.2f)
            addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                params.width = (startW + (endW - startW) * fraction).toInt()
                params.height = (startH + (endH - startH) * fraction).toInt()
                windowManager.updateViewLayout(floatingView, params)
            }
        }
        anim.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::floatingView.isInitialized) windowManager.removeView(floatingView)
    }
}

