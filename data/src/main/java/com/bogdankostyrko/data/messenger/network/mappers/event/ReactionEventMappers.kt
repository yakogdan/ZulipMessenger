package com.bogdankostyrko.data.messenger.network.mappers.event

import com.bogdankostyrko.data.messenger.network.dto.events.reaction.SingleReactionEventDTO
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventModel
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventOperation

private fun SingleReactionEventDTO.toModel(): ReactionEventModel =
    ReactionEventModel(
        operation = operation.toEventOperation(),
        userId = userId,
        messageId = messageId,
        emojiName = emojiName,
        emojiCode = emojiCode,
        id = id,
    )

fun List<SingleReactionEventDTO>.toModels() =
    this.map { it.toModel() }

private fun String.toEventOperation(): ReactionEventOperation =
    when (this) {
        "add" -> ReactionEventOperation.Add
        "remove" -> ReactionEventOperation.Remove
        else -> ReactionEventOperation.Unused
    }