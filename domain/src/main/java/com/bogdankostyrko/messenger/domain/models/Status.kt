package com.bogdankostyrko.messenger.domain.models

sealed interface Status {
    data object Active : Status
    data object Offline : Status
}

fun String.toStatus(): Status = if (this == "active") Status.Active else Status.Offline