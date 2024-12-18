package com.bogdankostyrko.messenger.presentation.adapters.channels.stream

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class StreamDelegateItem(
    val id: Int,
    private val value: StreamModel,
) : DelegateItem {

    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean =
        (other as StreamDelegateItem).value == content()
}