package com.bogdankostyrko.data.messenger.network.mappers.user

import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.data.messenger.network.dto.allusers.MemberDTO
import com.bogdankostyrko.messenger.domain.models.Status
import com.bogdankostyrko.messenger.domain.models.UserModel

private fun MemberDTO.toModel(status: Status): UserModel =
    UserModel(
        userId = userId,
        name = fullName,
        email = email,
        avatarUrl = avatarUrl,
        itsMe = userId == MY_USER_ID,
        status = status,
    )

fun List<MemberDTO>.toModels(activeUsers: List<String>): List<UserModel> =
    map {
        val status = if (it.email in activeUsers) Status.Active else Status.Offline
        it.toModel(status = status)
    }