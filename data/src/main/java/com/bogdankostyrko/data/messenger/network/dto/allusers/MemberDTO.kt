package com.bogdankostyrko.data.messenger.network.dto.allusers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberDTO(
    @SerialName("avatar_url")
    val avatarUrl: String?,
    @SerialName("email")
    val email: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("user_id")
    val userId: Int
)