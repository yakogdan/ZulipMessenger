package com.bogdankostyrko.messenger.presentation.adapters.chat.date

import com.bogdankostyrko.messenger.domain.models.DateModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class DateDelegateItem(
    private val value: DateModel,
) : DelegateItem {

    override fun content(): Any = value

    override fun id(): Int = value.hashCode()

    override fun compareToOther(other: DelegateItem): Boolean =
        (other as DateDelegateItem).value == content()
}