package com.bogdankostyrko.data.messenger.network.dto.messsages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageDTO(
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("client")
    val client: String,
    @SerialName("content")
    val content: String,
    @SerialName("content_type")
    val contentType: String,
    @SerialName("display_recipient")
    val displayRecipient: String,
    @SerialName("id")
    val id: Int,
    @SerialName("is_me_message")
    val isMeMessage: Boolean,
    @SerialName("reactions")
    val reactions: List<ReactionDTO>,
    @SerialName("recipient_id")
    val recipientId: Int,
    @SerialName("sender_email")
    val senderEmail: String,
    @SerialName("sender_full_name")
    val senderFullName: String,
    @SerialName("sender_id")
    val senderId: Int,
    @SerialName("sender_realm_str")
    val senderRealmStr: String,
    @SerialName("stream_id")
    val streamId: Int,
    @SerialName("subject")
    val subject: String,
    @SerialName("timestamp")
    val timestamp: Long,
    @SerialName("type")
    val type: String
)