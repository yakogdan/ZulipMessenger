package com.bogdankostyrko.messenger.domain.models.events.message

import com.bogdankostyrko.messenger.domain.models.MessageModel

data class MessageEventModel(
    val id: Int,
    val type: MessageEventType,
    val message: MessageModel?,
    val messageId: Int?,
    val content: String?,
)