package com.bogdankostyrko.data.messenger.network.tools

import com.bogdankostyrko.data.messenger.network.dto.narrow.NarrowDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

fun createNarrow(streamName: String): String {
    return Json.encodeToString(
        serializer(),
        listOf(
            NarrowDTO("stream", streamName)
        ),
    )
}

fun createNarrow(streamName: String, topicName: String): String {
    return Json.encodeToString(
        serializer(),
        listOf(
            NarrowDTO("stream", streamName),
            NarrowDTO("topic", topicName)
        ),
    )
}

fun getEventQueryMap(
    eventType: String,
    streamName: String,
    topicName: String?,
): Map<String, String> {

    val narrow = if (topicName != null) {
        listOf(
            arrayOf("stream", streamName),
            arrayOf("topic", topicName),
        )
    } else {
        listOf(arrayOf("stream", streamName))
    }

    val eventTypes = eventType.split(",")
    return mapOf(
        "narrow" to Json.encodeToString(narrow),
        "event_types" to Json.encodeToString(eventTypes)
    )
}