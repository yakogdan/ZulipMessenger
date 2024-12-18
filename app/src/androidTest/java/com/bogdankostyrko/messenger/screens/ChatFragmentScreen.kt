package com.bogdankostyrko.messenger.screens

import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.presentation.screens.chat.ChatFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object ChatFragmentScreen : KScreen<ChatFragmentScreen>() {

    override val layoutId: Int = R.layout.fragment_chat
    override val viewClass: Class<*> = ChatFragment::class.java

    val sendMessageButton = KButton { withId(R.id.btn_send_message) }
    val messageEditText = KEditText { withId(R.id.et_write_msg) }
}