package com.nakulov.exhentai.presentation

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import kotlin.math.floor

const val MATCH_PARENT = -1
const val WRAP_CONTENT = -2

fun dip(value: Int) = floor((density * value).toDouble()).toInt()
fun dip(value: Float) = floor((density * value).toDouble()).toFloat()

fun View.showKeyboard() = inputManager().showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
fun View.hideKeyboard() {
    if (!inputManager().isActive) return
    else
        inputManager().hideSoftInputFromWindow(windowToken, 0)
}

private fun View.inputManager(): InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun View.getViewBounds(): Rect {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return Rect(location[0], location[1], location[0] + width, location[1] + height)
}

operator fun ViewGroup.get(index: Int): View = getChildAt(index)

fun View.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(context, id)
fun Context.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(this, id)
fun View.getDrawableRes(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)
fun Context.getDrawableRes(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

fun View.font(fontName: String): Typeface = context.font(fontName)
fun Resources.font(fontName: String): Typeface = Typeface.createFromAsset(assets, fontName)
fun Context.font(fontName: String): Typeface = Typeface.createFromAsset(assets, fontName)

fun View.getStringRes(@StringRes id: Int) = resources.getString(id)
fun View.getStringRes(@StringRes id: Int, vararg any: Any) = resources.getString(id, *any)
fun TextView.setTextDpSize(value: Float) = setTextSize(TypedValue.COMPLEX_UNIT_DIP, value)