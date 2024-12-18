package com.bogdankostyrko.messenger.domain.models.events.message

sealed class MessageEventType {
    data object Add : MessageEventType()
    data object Delete : MessageEventType()
    data object Edit : MessageEventType()
    data object Unused : MessageEventType()
}