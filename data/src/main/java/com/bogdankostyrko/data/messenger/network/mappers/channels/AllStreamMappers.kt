package com.bogdankostyrko.data.messenger.network.mappers.channels

import com.bogdankostyrko.data.messenger.network.dto.allstreams.StreamDTO
import com.bogdankostyrko.messenger.domain.models.StreamModel

private fun StreamDTO.toModel(): StreamModel =
    StreamModel(
        id = streamId,
        streamName = name,
    )

fun List<StreamDTO>.toModels(): List<StreamModel> =
    map { it.toModel() }