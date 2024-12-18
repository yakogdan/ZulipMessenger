package com.bogdankostyrko.data.messenger.network.dto.userspresence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresenceInfo(
    @SerialName("status") val status: String,
)