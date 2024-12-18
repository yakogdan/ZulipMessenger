package com.bogdankostyrko.messenger.presentation.adapters.mappers

import com.bogdankostyrko.messenger.domain.models.TopicModel
import com.bogdankostyrko.messenger.presentation.adapters.channels.topic.TopicDelegateItem

private fun TopicModel.toDelegate(): TopicDelegateItem =
    TopicDelegateItem(
        id = id,
        value = this,
    )

fun List<TopicModel>.toDelegates(): List<TopicDelegateItem> =
    map { it.toDelegate() }