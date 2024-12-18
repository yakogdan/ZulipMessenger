package com.bogdankostyrko.messenger.presentation.screens.chat

import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.ElmStore
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ChatStoreFactory @Inject constructor(
    private val actor: ChatActor,
) {
    fun create(): Store<ChatEvent, ChatEffect, ChatState> {
        return ElmStore(
            initialState = ChatState(messages = LceState.Loading),
            reducer = ChatReducer(),
            actor = actor
        )
    }
}