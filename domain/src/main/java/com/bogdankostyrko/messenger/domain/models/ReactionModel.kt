package com.bogdankostyrko.messenger.domain.models

data class ReactionModel(
    val emojiCode: String,
    val emojiName: String,
    val userId: Int = 0,
    val isMyReaction: Boolean = false,
)