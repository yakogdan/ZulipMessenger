package com.bogdankostyrko.messenger.domain.models

data class StreamModel(
    val id: Int,
    val streamName: String,
    val isSelected: Boolean = false,
)