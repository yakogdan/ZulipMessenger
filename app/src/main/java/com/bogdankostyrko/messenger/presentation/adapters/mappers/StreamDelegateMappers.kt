package com.bogdankostyrko.messenger.presentation.adapters.mappers

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.presentation.adapters.channels.stream.StreamDelegateItem

//region toDelegate

private fun StreamModel.toDelegate(): StreamDelegateItem =
    StreamDelegateItem(
        id = id,
        value = this,
    )

fun List<StreamModel>.toDelegates(): List<StreamDelegateItem> =
    map { it.toDelegate() }

//endregion


//region toModel

private fun StreamDelegateItem.toModel(): StreamModel =
    StreamModel(
        id = id,
        streamName = (this.content() as StreamModel).streamName,
    )

fun List<StreamDelegateItem>.toModels(): List<StreamModel> =
    map { it.toModel() }

//endregion