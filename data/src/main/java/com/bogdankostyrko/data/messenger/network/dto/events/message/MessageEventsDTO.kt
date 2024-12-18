package com.bogdankostyrko.data.messenger.network.dto.events.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageEventsDTO(
    @SerialName("result")
    val result: String,
    @SerialName("msg")
    val msg: String,
    @SerialName("events")
    val events: List<SingleMessageEventDTO>,
)
