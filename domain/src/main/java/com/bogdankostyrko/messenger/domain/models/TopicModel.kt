package com.bogdankostyrko.messenger.domain.models

data class TopicModel(
    val id: Int,
    val topicName: String,
    val parentStreamName: String,
)