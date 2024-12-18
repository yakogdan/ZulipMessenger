package com.bogdankostyrko.messenger.tools

import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat

fun setStatusBarColor(
    activity: Activity?,
    context: Context,
    colorResId: Int
) {
    activity?.window?.statusBarColor =
        ContextCompat.getColor(context, colorResId)
}