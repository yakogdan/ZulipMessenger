package com.bogdankostyrko.messenger.domain.models

data class DateModel(
    val day: Int,
    val month: Int,
)

fun DateModel.toDayAndMonth() = "$day.$month"