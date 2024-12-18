package com.bogdankostyrko.data.messenger.network.mappers.channels

import com.bogdankostyrko.data.messenger.network.dto.topics.TopicDTO
import com.bogdankostyrko.messenger.domain.models.TopicModel

private fun TopicDTO.toModel(parentStreamName: String): TopicModel =
    TopicModel(
        id = maxId,
        topicName = name,
        parentStreamName = parentStreamName,
    )

fun List<TopicDTO>.toModels(parentStreamName: String): List<TopicModel> =
    map { it.toModel(parentStreamName) }