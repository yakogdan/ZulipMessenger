package com.bogdankostyrko.data.messenger.network.dto.events.message

import com.bogdankostyrko.data.messenger.network.dto.messsages.MessageDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleMessageEventDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("type")
    val type: String,
    @SerialName("message")
    val message: MessageDTO? = null,
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("content")
    val content: String? = null,
)