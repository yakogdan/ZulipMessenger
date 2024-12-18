package com.bogdankostyrko.data.messenger.network.dto.allusers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllUsersRootDTO(
    @SerialName("members")
    val members: List<MemberDTO>,
    @SerialName("msg")
    val msg: String,
    @SerialName("result")
    val result: String
)