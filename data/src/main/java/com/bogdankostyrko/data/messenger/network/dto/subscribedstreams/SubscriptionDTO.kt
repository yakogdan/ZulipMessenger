package com.bogdankostyrko.data.messenger.network.dto.subscribedstreams


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionDTO(
    @SerialName("audible_notifications")
    val audibleNotifications: Boolean?,
    @SerialName("can_remove_subscribers_group")
    val canRemoveSubscribersGroup: Int,
    @SerialName("color")
    val color: String,
    @SerialName("date_created")
    val dateCreated: Int,
    @SerialName("description")
    val description: String,
    @SerialName("desktop_notifications")
    val desktopNotifications: Boolean?,
    @SerialName("email_notifications")
    val emailNotifications: Boolean?,
    @SerialName("first_message_id")
    val firstMessageId: Int,
    @SerialName("history_public_to_subscribers")
    val historyPublicToSubscribers: Boolean,
    @SerialName("in_home_view")
    val inHomeView: Boolean,
    @SerialName("invite_only")
    val inviteOnly: Boolean,
    @SerialName("is_announcement_only")
    val isAnnouncementOnly: Boolean,
    @SerialName("is_muted")
    val isMuted: Boolean,
    @SerialName("is_web_public")
    val isWebPublic: Boolean,
    @SerialName("message_retention_days")
    val messageRetentionDays: Int?,
    @SerialName("name")
    val name: String,
    @SerialName("pin_to_top")
    val pinToTop: Boolean,
    @SerialName("push_notifications")
    val pushNotifications: Boolean?,
    @SerialName("rendered_description")
    val renderedDescription: String,
    @SerialName("stream_id")
    val streamId: Int,
    @SerialName("stream_post_policy")
    val streamPostPolicy: Int,
    @SerialName("stream_weekly_traffic")
    val streamWeeklyTraffic: Int?,
    @SerialName("wildcard_mentions_notify")
    val wildcardMentionsNotify: Boolean?
)