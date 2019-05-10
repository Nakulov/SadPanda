package com.nakulov.exhentai.presentation

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import com.nakulov.exhentai.presentation.ExApplication.Companion.applicationHandler
import kotlin.math.max
import kotlin.math.min

var density = 1.0f
var scaledDensity = 1f
var usingHardwareInput = false
var displaySize = Point()
var displayMetrics = DisplayMetrics()

fun Context.calculateDensity() {
    val configuration = resources.configuration

    density = resources.displayMetrics.density
    scaledDensity = resources.displayMetrics.scaledDensity

    usingHardwareInput = configuration.keyboard != Configuration.KEYBOARD_NOKEYS && configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO

    val manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    val display = manager.defaultDisplay

    if (display != null) {

        display.getMetrics(displayMetrics)
        display.getSize(displaySize)

    }

    if (configuration.screenWidthDp != Configuration.SCREEN_WIDTH_DP_UNDEFINED) {
        val newSize: Int = Math.ceil((configuration.screenWidthDp * density).toDouble()).toInt()
        if (Math.abs(displaySize.x - newSize) > 3) {
            displaySize.x = newSize
        }
    }

    if (configuration.screenHeightDp != Configuration.SCREEN_HEIGHT_DP_UNDEFINED) {
        val newSize: Int = Math.ceil((configuration.screenHeightDp * density).toDouble()).toInt()
        if (Math.abs(displaySize.y - newSize) > 3) {
            displaySize.y = newSize
        }
    }

    val screenWidth = min(displaySize.x, displaySize.y)
    val screenHeight = max(displaySize.x, displaySize.y)

    displaySize.x = screenWidth
    displaySize.y = screenHeight
}

fun cancelRunOnUIThread(runnable: Runnable?) {
    applicationHandler.removeCallbacks(runnable)
}

fun runOnUiThread(runnable: Runnable, delay: Long) {
    applicationHandler.postDelayed(runnable, delay)
}

inline fun runOnUiThread(crossinline block: () -> Unit, delay: Long = 0): Runnable {
    val runnable = Runnable {
        block()
    }
    if (delay == 0L) applicationHandler.post(runnable)
    else applicationHandler.postDelayed(runnable, delay)

    return runnable
}