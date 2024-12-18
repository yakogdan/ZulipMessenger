package com.bogdankostyrko.data.messenger.network.mappers.chat

import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.data.messenger.network.dto.messsages.ReactionDTO
import com.bogdankostyrko.messenger.domain.models.ReactionModel

fun ReactionDTO.toModel(): ReactionModel =
    ReactionModel(
        emojiCode = emojiCode.getEmoji(),
        emojiName = emojiName,
        userId = userId,
        isMyReaction = userId == MY_USER_ID,
    )

fun List<ReactionDTO>.toModels(): List<ReactionModel> =
    map { it.toModel() }

fun String.getEmoji() = String(Character.toChars(this.toInt(16)))