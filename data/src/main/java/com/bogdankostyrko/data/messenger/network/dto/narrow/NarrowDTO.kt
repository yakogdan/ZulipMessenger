package com.bogdankostyrko.data.messenger.network.dto.narrow

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NarrowDTO(
    @SerialName("operator")
    val operator: String,
    @SerialName("operand")
    val operand: String,
)