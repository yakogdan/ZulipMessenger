package com.bogdankostyrko.messenger.screens

import android.view.View
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.presentation.screens.channels.ChannelsFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object ChannelsFragmentScreen : KScreen<ChannelsFragmentScreen>() {

    override val layoutId: Int = R.layout.fragment_channels
    override val viewClass: Class<*> = ChannelsFragment::class.java

    val channelsRecyclerView = KRecyclerView(
        builder = { withId(R.id.rv_channels) },
        itemTypeBuilder = {
            itemType(::KStreamItem)
            itemType(::KTopicItem)
        }
    )

    class KStreamItem(parent: Matcher<View>) : KRecyclerItem<KStreamItem>(parent) {
        val streamTitle = KTextView(parent) { withId(R.id.tv_stream_title) }
    }

    class KTopicItem(parent: Matcher<View>) : KRecyclerItem<KTopicItem>(parent) {
        val topicTitle = KTextView(parent) { withId(R.id.tv_topic_title) }
    }
}