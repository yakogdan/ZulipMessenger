package com.bogdankostyrko.messenger.domain.models

data class UserModel(
    val userId: Int,
    val name: String,
    val email: String,
    val avatarUrl: String?,
    val itsMe: Boolean = false,
    val status: Status = Status.Offline,
)