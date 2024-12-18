package com.bogdankostyrko.data.messenger.network.dto.allstreams


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllStreamsRootDTO(
    @SerialName("msg")
    val msg: String,
    @SerialName("result")
    val result: String,
    @SerialName("streams")
    val streams: List<StreamDTO>
)