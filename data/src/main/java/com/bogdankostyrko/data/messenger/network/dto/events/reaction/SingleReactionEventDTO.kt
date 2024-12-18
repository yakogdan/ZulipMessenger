package com.bogdankostyrko.data.messenger.network.dto.events.reaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleReactionEventDTO(
    @SerialName("op")
    val operation: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("message_id")
    val messageId: Int,
    @SerialName("emoji_name")
    val emojiName: String,
    @SerialName("emoji_code")
    val emojiCode: String,
    @SerialName("id")
    val id: Int,
)