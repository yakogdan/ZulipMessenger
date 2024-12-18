package com.bogdankostyrko.messenger.presentation.screens.people

import com.bogdankostyrko.messenger.domain.models.Status
import com.bogdankostyrko.messenger.domain.usecases.people.GetAllUsersUseCase
import com.bogdankostyrko.messenger.domain.usecases.people.UpdateYourPresenceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class PeopleActor @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val updateYourPresenceUseCase: UpdateYourPresenceUseCase,
) : Actor<PeopleCommand, PeopleEvent>() {

    override fun execute(command: PeopleCommand): Flow<PeopleEvent> {
        return when (command) {
            is PeopleCommand.LoadAllUsers -> getAllUsers()
            is PeopleCommand.UpdateYourPresence -> updateYourPresence(status = command.status)
        }
    }

    private fun getAllUsers() = flow {
        runCatching {
            getAllUsersUseCase.invoke()
        }.fold(
            onSuccess = { users ->
                emit(
                    PeopleEvent.Domain.DataLoaded(
                        users = users.sortedByDescending { it.status is Status.Active }
                    )
                )
            },
            onFailure = {
                emit(PeopleEvent.Domain.Error(throwable = it))
            }
        )
    }

    private fun updateYourPresence(status: String) = flow {
        runCatching {
            updateYourPresenceUseCase.invoke(status = status)
        }.onFailure {
            emit(PeopleEvent.Domain.Error(throwable = it))
        }
    }
}