package com.bogdankostyrko.data.messenger.network.dto.messsages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessagesRootDTO(
    @SerialName("anchor")
    val anchor: Long,
    @SerialName("found_anchor")
    val foundAnchor: Boolean,
    @SerialName("found_newest")
    val foundNewest: Boolean,
    @SerialName("found_oldest")
    val foundOldest: Boolean,
    @SerialName("history_limited")
    val historyLimited: Boolean,
    @SerialName("messages")
    val messages: List<MessageDTO>,
    @SerialName("msg")
    val msg: String,
    @SerialName("result")
    val result: String,
)