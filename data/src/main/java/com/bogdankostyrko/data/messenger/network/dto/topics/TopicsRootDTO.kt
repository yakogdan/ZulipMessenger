package com.bogdankostyrko.data.messenger.network.dto.topics


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicsRootDTO(
    @SerialName("msg")
    val msg: String,
    @SerialName("result")
    val result: String,
    @SerialName("topics")
    val topics: List<TopicDTO>
)