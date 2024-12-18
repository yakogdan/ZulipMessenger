package com.bogdankostyrko.messenger.presentation.screens.channels

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.presentation.adapters.channels.stream.StreamDelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

fun changeIsSelectedStatus(
    currentStream: DelegateItem,
    desiredValue: Boolean,
    streams: MutableList<DelegateItem>,
    currentStreamIndex: Int
) {
    val modifiedStreamModel =
        (currentStream.content() as StreamModel).copy(isSelected = desiredValue)
    val modifiedCurrentStream =
        StreamDelegateItem(id = currentStream.id(), value = modifiedStreamModel)
    streams[currentStreamIndex] = modifiedCurrentStream
}