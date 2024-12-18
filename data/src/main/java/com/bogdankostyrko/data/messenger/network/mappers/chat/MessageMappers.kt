package com.bogdankostyrko.data.messenger.network.mappers.chat

import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.data.messenger.network.dto.messsages.MessageDTO
import com.bogdankostyrko.data.messenger.network.mappers.toDay
import com.bogdankostyrko.data.messenger.network.mappers.toMonth
import com.bogdankostyrko.messenger.domain.models.MessageModel

fun MessageDTO.toModel(): MessageModel =
    MessageModel(
        id = id,
        avatarUrl = avatarUrl,
        userName = senderFullName,
        text = content,
        reactions = reactions.toModels(),
        day = timestamp.toDay(),
        month = timestamp.toMonth(),
        timestamp = timestamp,
        isMyMessage = senderId == MY_USER_ID,
    )

fun List<MessageDTO>.toModels() =
    map { it.toModel() }