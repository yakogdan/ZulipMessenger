package com.bogdankostyrko.data.messenger.network.dto.allstreams


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamDTO(
    @SerialName("can_remove_subscribers_group")
    val canRemoveSubscribersGroup: Int,
    @SerialName("date_created")
    val dateCreated: Int,
    @SerialName("description")
    val description: String,
    @SerialName("first_message_id")
    val firstMessageId: Int,
    @SerialName("history_public_to_subscribers")
    val historyPublicToSubscribers: Boolean,
    @SerialName("invite_only")
    val inviteOnly: Boolean,
    @SerialName("is_announcement_only")
    val isAnnouncementOnly: Boolean,
    @SerialName("is_web_public")
    val isWebPublic: Boolean,
    @SerialName("message_retention_days")
    val messageRetentionDays: Int?,
    @SerialName("name")
    val name: String,
    @SerialName("rendered_description")
    val renderedDescription: String,
    @SerialName("stream_id")
    val streamId: Int,
    @SerialName("stream_post_policy")
    val streamPostPolicy: Int,
    @SerialName("stream_weekly_traffic")
    val streamWeeklyTraffic: Int?
)