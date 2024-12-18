package com.bogdankostyrko.data.messenger.network.dto.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisteredEventQueueDTO(
    @SerialName("last_event_id")
    val lastEventId: Int,
    @SerialName("queue_id")
    val queueId: String,
)