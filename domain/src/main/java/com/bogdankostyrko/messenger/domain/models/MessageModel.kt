package com.bogdankostyrko.messenger.domain.models

data class MessageModel(
    val id: Int,
    val avatarUrl: String,
    val userName: String,
    val text: String,
    val reactions: List<ReactionModel>,
    val day: Int,
    val month: Int,
    val timestamp: Long,
    val isMyMessage: Boolean,
)