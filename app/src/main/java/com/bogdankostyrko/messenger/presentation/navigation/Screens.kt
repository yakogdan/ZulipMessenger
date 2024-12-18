package com.bogdankostyrko.messenger.presentation.navigation

import com.bogdankostyrko.messenger.presentation.screens.channels.ChannelsFragment
import com.bogdankostyrko.messenger.presentation.screens.chat.ChatFragment
import com.bogdankostyrko.messenger.presentation.screens.people.PeopleFragment
import com.bogdankostyrko.messenger.presentation.screens.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun Channels(): FragmentScreen = FragmentScreen { ChannelsFragment() }

    fun Chat(
        streamName: String,
        topicName: String? = null,
    ): FragmentScreen = FragmentScreen {
        ChatFragment.getInstance(
            streamName = streamName,
            topicName = topicName
        )
    }

    fun People(): FragmentScreen = FragmentScreen { PeopleFragment() }

    fun Profile(userId: Int): FragmentScreen =
        FragmentScreen { ProfileFragment.getInstance(userId) }
}