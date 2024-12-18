package com.bogdankostyrko.data.messenger.network.dto.events.reaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionEventsDTO(
    @SerialName("result")
    val result: String,
    @SerialName("msg")
    val msg: String,
    @SerialName("events")
    val events: List<SingleReactionEventDTO>
)