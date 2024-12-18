package com.bogdankostyrko.messenger.presentation.screens.chat

import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

sealed interface ChatScreenState {

    data object Initial : ChatScreenState

    data object Loading : ChatScreenState

    data object Error : ChatScreenState

    data object Empty : ChatScreenState

    data class Success(val messages: List<DelegateItem>) : ChatScreenState
}