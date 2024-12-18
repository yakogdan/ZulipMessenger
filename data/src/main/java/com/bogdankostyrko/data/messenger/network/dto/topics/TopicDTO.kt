package com.bogdankostyrko.data.messenger.network.dto.topics


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicDTO(
    @SerialName("max_id")
    val maxId: Int,
    @SerialName("name")
    val name: String
)