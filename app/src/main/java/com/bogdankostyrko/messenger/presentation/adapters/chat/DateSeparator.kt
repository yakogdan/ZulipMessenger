package com.bogdankostyrko.messenger.presentation.adapters.chat

import com.bogdankostyrko.messenger.domain.models.DateModel
import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.presentation.adapters.chat.date.DateDelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.mappers.toDelegate

fun List<MessageModel>.toMessagesWithDateSeparator(): List<DelegateItem> = this
    .sortedWith(compareBy({ it.month }, { it.day }))
    .groupBy { Pair(it.month, it.day) }
    .flatMap { (monthDayPair, messages) ->
        val dateModel =
            DateModel(monthDayPair.second, monthDayPair.first)
        val delegateItems = mutableListOf<DelegateItem>()
        delegateItems.add(DateDelegateItem(dateModel))
        for (message in messages) {
            delegateItems.add(message.toDelegate())
        }
        delegateItems
    }