package com.bogdankostyrko.messenger.presentation.screens.profile

import com.bogdankostyrko.messenger.domain.usecases.people.UpdateYourPresenceUseCase
import com.bogdankostyrko.messenger.domain.usecases.profile.GetUserByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ProfileActor @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val updateYourPresenceUseCase: UpdateYourPresenceUseCase,
) : Actor<ProfileCommand, ProfileEvent>() {

    override fun execute(command: ProfileCommand): Flow<ProfileEvent> {
        return when (command) {
            is ProfileCommand.LoadUser -> getUser(userId = command.userId)
            is ProfileCommand.UpdateYourPresence -> updateYourPresence(status = command.status)
        }
    }

    private fun getUser(userId: Int) = flow {
        runCatching {
            getUserByIdUseCase.invoke(userId)
        }.fold(
            onSuccess = { user ->
                emit(ProfileEvent.Domain.DataLoaded(user = user))
            },
            onFailure = {
                emit(ProfileEvent.Domain.Error(it))
            }
        )
    }

    private fun updateYourPresence(status: String) = flow {
        runCatching {
            updateYourPresenceUseCase.invoke(status = status)
        }.onFailure {
            emit(ProfileEvent.Domain.Error(it))
        }
    }
}