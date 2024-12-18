package com.bogdankostyrko.data.messenger.network.mappers.channels

import com.bogdankostyrko.data.messenger.network.dto.subscribedstreams.SubscriptionDTO
import com.bogdankostyrko.messenger.domain.models.StreamModel

private fun SubscriptionDTO.toModel(): StreamModel =
    StreamModel(
        id = streamId,
        streamName = name,
    )

fun List<SubscriptionDTO>.toModels(): List<StreamModel> =
    map { it.toModel() }