package com.bogdankostyrko.messenger.presentation.screens.people

import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class PeopleReducer : ScreenDslReducer<
        PeopleEvent,
        PeopleEvent.Ui,
        PeopleEvent.Domain,
        PeopleState,
        PeopleEffect,
        PeopleCommand,
        >(
    PeopleEvent.Ui::class,
    PeopleEvent.Domain::class,
) {

    override fun Result.internal(event: PeopleEvent.Domain) = when (event) {

        is PeopleEvent.Domain.DataLoaded -> {
            state { copy(users = LceState.Content(event.users)) }
        }

        is PeopleEvent.Domain.Error -> {
            state { copy(users = LceState.Error) }
            effects { +PeopleEffect.ShowError }
        }
    }

    override fun Result.ui(event: PeopleEvent.Ui) = when (event) {

        PeopleEvent.Ui.LoadAllUsers -> {
            state { copy(users = LceState.Loading) }
            commands { +PeopleCommand.LoadAllUsers }
        }

        is PeopleEvent.Ui.OnUserItemClick -> {
            effects { +PeopleEffect.NavigateToUser(user = event.user) }
        }

        is PeopleEvent.Ui.UpdateYourPresence -> {
            commands { +PeopleCommand.UpdateYourPresence(status = event.status) }
        }
    }
}