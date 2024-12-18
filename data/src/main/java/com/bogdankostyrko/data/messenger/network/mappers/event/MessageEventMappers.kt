package com.bogdankostyrko.data.messenger.network.mappers.event

import com.bogdankostyrko.data.messenger.network.dto.events.message.SingleMessageEventDTO
import com.bogdankostyrko.data.messenger.network.mappers.chat.toModel
import com.bogdankostyrko.messenger.domain.models.events.message.MessageEventModel
import com.bogdankostyrko.messenger.domain.models.events.message.MessageEventType

private fun SingleMessageEventDTO.toModel(): MessageEventModel =
    MessageEventModel(
        id = id,
        type = type.toEventType(),
        message = message?.toModel(),
        messageId = messageId,
        content = content,
    )

fun List<SingleMessageEventDTO>.toModels() =
    this.map { it.toModel() }

private fun String.toEventType(): MessageEventType =
    when (this) {
        "message" -> MessageEventType.Add
        "delete_message" -> MessageEventType.Delete
        "update_message" -> MessageEventType.Edit
        else -> MessageEventType.Unused
    }