package com.bogdankostyrko.messenger.presentation.screens.profile

import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.presentation.elm.LceState

data class ProfileState(
    val user: LceState<UserModel>,
)

sealed interface ProfileEvent {

    sealed interface Ui : ProfileEvent {

        data class LoadUser(val userId: Int) : Ui
        data object OnBackButtonClick : Ui
        data class UpdateYourPresence(val status: String) : Ui
    }

    sealed interface Domain : ProfileEvent {

        data class DataLoaded(val user: UserModel) : Domain
        data class Error(val throwable: Throwable) : Domain
    }
}

sealed interface ProfileCommand {

    data class LoadUser(val userId: Int) : ProfileCommand
    data class UpdateYourPresence(val status: String) : ProfileCommand
}

sealed interface ProfileEffect {

    data object ShowError : ProfileEffect
    data object NavigateToBack : ProfileEffect
}