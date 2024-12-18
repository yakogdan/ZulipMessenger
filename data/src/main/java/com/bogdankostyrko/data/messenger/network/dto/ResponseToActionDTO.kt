package com.bogdankostyrko.data.messenger.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseToActionDTO(
    @SerialName("msg")
    val msg: String? = null,
    @SerialName("result")
    val result: String,
)