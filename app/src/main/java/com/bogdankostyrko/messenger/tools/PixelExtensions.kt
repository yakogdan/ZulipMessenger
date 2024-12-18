package com.bogdankostyrko.messenger.tools

import android.content.Context
import android.util.TypedValue

fun Int.convertSpToPixels(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    this.toFloat(),
    context.resources.displayMetrics
).toInt()

fun Int.convertDpToPixels(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context.resources.displayMetrics
).toInt()