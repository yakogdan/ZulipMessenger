package com.bogdankostyrko.data.messenger.network.dto.userspresence


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleUserPresence(
    @SerialName("presence") val presence: Presence,
    @SerialName("result") val result: String,
)