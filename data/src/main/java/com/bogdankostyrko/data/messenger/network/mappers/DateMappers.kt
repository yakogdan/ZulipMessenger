package com.bogdankostyrko.data.messenger.network.mappers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDay(): Int {
    val date = Date(this.toTimestampMillis())
    val dayFormat = SimpleDateFormat("d", Locale.getDefault())
    val day = dayFormat.format(date).toInt()

    return day
}

fun Long.toMonth(): Int {
    val date = Date(this.toTimestampMillis())
    val monthFormat = SimpleDateFormat("M", Locale.getDefault())
    val month = monthFormat.format(date).toInt()

    return month
}

fun Long.toTimestampMillis() = this * 1000L