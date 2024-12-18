package com.bogdankostyrko.messenger.presentation.screens.channels

import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

sealed interface ChannelsScreenState {

    data object Initial : ChannelsScreenState

    data object Loading : ChannelsScreenState

    data object Error : ChannelsScreenState

    data object Empty : ChannelsScreenState

    data class Success(val streams: List<DelegateItem>) : ChannelsScreenState
}