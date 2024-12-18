package com.bogdankostyrko.messenger.domain.models.events

data class RegisteredEventQueueModel(
    val lastEventId: Int,
    val queueId: String,
)