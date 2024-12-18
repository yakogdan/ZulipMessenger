package com.bogdankostyrko.data.messenger.network.mappers.event

import com.bogdankostyrko.data.messenger.network.dto.events.RegisteredEventQueueDTO
import com.bogdankostyrko.messenger.domain.models.events.RegisteredEventQueueModel

fun RegisteredEventQueueDTO.toModel(): RegisteredEventQueueModel =
    RegisteredEventQueueModel(
        lastEventId = lastEventId,
        queueId = queueId,
    )