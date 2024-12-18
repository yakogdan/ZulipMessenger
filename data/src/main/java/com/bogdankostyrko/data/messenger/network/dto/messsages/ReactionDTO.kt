package com.bogdankostyrko.data.messenger.network.dto.messsages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionDTO(
    @SerialName("emoji_code")
    val emojiCode: String,
    @SerialName("emoji_name")
    val emojiName: String,
    @SerialName("reaction_type")
    val reactionType: String,
    @SerialName("user")
    val user: UserDTO,
    @SerialName("user_id")
    val userId: Int,
)