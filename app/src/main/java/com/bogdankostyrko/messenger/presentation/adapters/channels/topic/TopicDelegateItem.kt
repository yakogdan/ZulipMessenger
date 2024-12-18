package com.bogdankostyrko.messenger.presentation.adapters.channels.topic

import com.bogdankostyrko.messenger.domain.models.TopicModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class TopicDelegateItem(
    val id: Int,
    private val value: TopicModel,
) : DelegateItem {

    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean =
        (other as TopicDelegateItem).value == content()
}