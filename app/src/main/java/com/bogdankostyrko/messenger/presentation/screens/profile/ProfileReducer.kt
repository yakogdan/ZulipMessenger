package com.bogdankostyrko.messenger.presentation.screens.profile

import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class ProfileReducer : ScreenDslReducer<
        ProfileEvent,
        ProfileEvent.Ui,
        ProfileEvent.Domain,
        ProfileState,
        ProfileEffect,
        ProfileCommand,
        >(
    ProfileEvent.Ui::class,
    ProfileEvent.Domain::class,
) {

    override fun Result.internal(event: ProfileEvent.Domain) = when (event) {

        is ProfileEvent.Domain.DataLoaded -> {
            state { copy(user = LceState.Content(event.user)) }
        }

        is ProfileEvent.Domain.Error -> {
            state { copy(user = LceState.Error) }
            effects { +ProfileEffect.ShowError }
        }
    }

    override fun Result.ui(event: ProfileEvent.Ui) = when (event) {

        is ProfileEvent.Ui.LoadUser -> {
            state { copy(user = LceState.Loading) }
            commands { +ProfileCommand.LoadUser(userId = event.userId) }
        }

        ProfileEvent.Ui.OnBackButtonClick -> {
            effects { +ProfileEffect.NavigateToBack }
        }

        is ProfileEvent.Ui.UpdateYourPresence -> {
            commands { +ProfileCommand.UpdateYourPresence(status = event.status) }
        }
    }
}