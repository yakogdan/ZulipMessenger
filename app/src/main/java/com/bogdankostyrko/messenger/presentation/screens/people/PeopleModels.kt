package com.bogdankostyrko.messenger.presentation.screens.people

import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.presentation.elm.LceState

data class PeopleState(
    val users: LceState<List<UserModel>>,
)

sealed interface PeopleEvent {

    sealed interface Ui : PeopleEvent {

        data object LoadAllUsers : Ui
        data class OnUserItemClick(val user: UserModel) : Ui
        data class UpdateYourPresence(val status: String) : Ui
    }

    sealed interface Domain : PeopleEvent {

        data class DataLoaded(val users: List<UserModel>) : Domain
        data class Error(val throwable: Throwable) : Domain
    }
}

sealed interface PeopleCommand {

    data object LoadAllUsers : PeopleCommand
    data class UpdateYourPresence(val status: String) : PeopleCommand
}

sealed interface PeopleEffect {

    data object ShowError : PeopleEffect
    data class NavigateToUser(val user: UserModel) : PeopleEffect
}