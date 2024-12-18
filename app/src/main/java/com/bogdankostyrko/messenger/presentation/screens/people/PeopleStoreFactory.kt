package com.bogdankostyrko.messenger.presentation.screens.people

import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.ElmStore
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class PeopleStoreFactory @Inject constructor(
    private val actor: PeopleActor,
) {
    fun create(): Store<PeopleEvent, PeopleEffect, PeopleState> {
        return ElmStore(
            initialState = PeopleState(
                users = LceState.Loading,
            ),
            reducer = PeopleReducer(),
            actor = actor
        )
    }
}