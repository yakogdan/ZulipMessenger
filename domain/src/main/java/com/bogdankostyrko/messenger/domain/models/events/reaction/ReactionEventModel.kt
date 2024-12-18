package com.bogdankostyrko.messenger.domain.models.events.reaction

data class ReactionEventModel(
    val operation: ReactionEventOperation,
    val userId: Int,
    val messageId: Int,
    val emojiName: String,
    val emojiCode: String,
    val id: Int,
)