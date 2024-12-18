package com.bogdankostyrko.messenger.presentation.screens.profile

import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.ElmStore
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ProfileStoreFactory @Inject constructor(
    private val actor: ProfileActor,
) {
    fun create(): Store<ProfileEvent, ProfileEffect, ProfileState> {
        return ElmStore(
            initialState = ProfileState(
                user = LceState.Loading,
            ),
            reducer = ProfileReducer(),
            actor = actor
        )
    }
}