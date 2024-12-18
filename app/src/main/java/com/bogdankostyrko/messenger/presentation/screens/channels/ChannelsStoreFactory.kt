package com.bogdankostyrko.messenger.presentation.screens.channels

import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.ElmStore
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ChannelsStoreFactory @Inject constructor(
    private val actor: ChannelsActor,
) {
    fun create(): Store<ChannelsEvent, ChannelsEffect, ChannelsState> {
        return ElmStore(
            initialState = ChannelsState(
                channels = LceState.Loading,
                isSubscribedStreams = true,
            ),
            reducer = ChannelsReducer(),
            actor = actor,
        )
    }
}