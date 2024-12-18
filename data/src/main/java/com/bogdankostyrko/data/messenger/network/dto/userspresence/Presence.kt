package com.bogdankostyrko.data.messenger.network.dto.userspresence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Presence(
    @SerialName("aggregated") val presenceInfo: PresenceInfo,
)