package com.bogdankostyrko.data.messenger.database.mappers

import com.bogdankostyrko.data.messenger.database.models.StreamDBO
import com.bogdankostyrko.messenger.domain.models.StreamModel

private fun StreamDBO.toModel(): StreamModel =
    StreamModel(
        id = id,
        streamName = title,
    )

fun List<StreamDBO>.toModels(): List<StreamModel> =
    map { it.toModel() }


private fun StreamModel.toDBO(isSubscribed: Boolean): StreamDBO =
    StreamDBO(
        id = id,
        title = streamName,
        isSubscribed = isSubscribed,
    )

fun List<StreamModel>.toDBO(isSubscribed: Boolean): List<StreamDBO> =
    map { it.toDBO(isSubscribed) }