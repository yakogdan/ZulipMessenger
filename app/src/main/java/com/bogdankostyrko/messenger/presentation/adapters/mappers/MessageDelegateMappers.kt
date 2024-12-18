package com.bogdankostyrko.messenger.presentation.adapters.mappers

import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.presentation.adapters.chat.message.MessageDelegateItem

fun MessageModel.toDelegate(): MessageDelegateItem =
    MessageDelegateItem(id = id, value = this)

fun List<MessageModel>.toDelegates(): List<MessageDelegateItem> =
    map { it.toDelegate() }