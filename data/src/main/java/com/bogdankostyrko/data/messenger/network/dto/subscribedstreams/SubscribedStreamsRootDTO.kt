package com.bogdankostyrko.data.messenger.network.dto.subscribedstreams


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubscribedStreamsRootDTO(
    @SerialName("msg")
    val msg: String,
    @SerialName("result")
    val result: String,
    @SerialName("subscriptions")
    val subscriptions: List<SubscriptionDTO>
)