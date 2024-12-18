package com.bogdankostyrko.data.messenger.network.dto.singleuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleUserRootDTO(
    @SerialName("msg")
    val msg: String,
    @SerialName("result")
    val result: String,
    @SerialName("user")
    val user: SingleUserDTO
)