package com.bogdankostyrko.data.messenger.network.mappers.user

import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.data.messenger.network.dto.singleuser.SingleUserDTO
import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.domain.models.toStatus

fun SingleUserDTO.toModel(status: String): UserModel =
    UserModel(
        userId = userId,
        name = fullName,
        email = email,
        avatarUrl = avatarUrl,
        itsMe = userId == MY_USER_ID,
        status = status.toStatus()
    )