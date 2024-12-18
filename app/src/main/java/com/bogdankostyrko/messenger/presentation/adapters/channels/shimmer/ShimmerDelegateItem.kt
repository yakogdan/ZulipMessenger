package com.bogdankostyrko.messenger.presentation.adapters.channels.shimmer

import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class ShimmerDelegateItem(
    val id: Int,
    private val value: String = "",
) : DelegateItem {

    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean =
        (other as ShimmerDelegateItem).value == content()
}