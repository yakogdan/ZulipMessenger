package com.bogdankostyrko.data.messenger.network.dto.userspresence


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllUsersPresence(
    @SerialName("presences") val presences: Map<String, Presence>,
    @SerialName("result") val result: String,
)