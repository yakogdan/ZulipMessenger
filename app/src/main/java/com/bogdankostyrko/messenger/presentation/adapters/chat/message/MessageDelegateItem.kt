package com.bogdankostyrko.messenger.presentation.adapters.chat.message

import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class MessageDelegateItem(
    val id: Int,
    private val value: MessageModel,
) : DelegateItem {

    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean =
        (other as MessageDelegateItem).value == content()
}