package com.salapb.app.service

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import com.salapb.app.R
import com.salapb.app.ui.BotDashboardActivity

class BotFloatingService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: FrameLayout
    private var x = 0f
    private var y = 0f

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        floatingView = FrameLayout(this)
        
        val botIcon = ImageView(this)
        botIcon.setImageResource(R.drawable.ic_launcher_background)
        botIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
        
        floatingView.addView(botIcon, FrameLayout.LayoutParams(
            80, 80, Gravity.CENTER
        ))
        
        val params = WindowManager.LayoutParams().apply {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            format = PixelFormat.TRANSLUCENT
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            width = 100
            height = 100
            gravity = Gravity.TOP or Gravity.END
            x = 10
            y = 100
        }
        
        windowManager.addView(floatingView, params)
        
        floatingView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    x = event.rawX
                    y = event.rawY
                }
                MotionEvent.ACTION_UP -> {
                    if (x == event.rawX && y == event.rawY) {
                        startActivity(
                            Intent(this, BotDashboardActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                        )
                    }
                }
            }
            true
        }
        
        return START_STICKY
    }

    override fun onDestroy() {
        if (::windowManager != null && ::floatingView != null) {
            try {
                windowManager.removeView(floatingView)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        super.onDestroy()
    }
}
