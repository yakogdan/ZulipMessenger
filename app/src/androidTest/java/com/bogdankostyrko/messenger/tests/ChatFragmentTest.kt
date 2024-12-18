package com.bogdankostyrko.messenger.tests

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bogdankostyrko.messenger.mock.MockMessages.Companion.messages
import com.bogdankostyrko.messenger.presentation.activities.MainActivity
import com.bogdankostyrko.messenger.scenarios.NavigateToChatScenario
import com.bogdankostyrko.messenger.screens.ChatFragmentScreen
import com.bogdankostyrko.messenger.utils.AppTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChatFragmentTest : TestCase() {

    @get:Rule
    val rule = AppTestRule<MainActivity>(getIntent()) { }

    @Test
    fun sendButtonIsClickable() = run {
        rule.wiremockRule.messages { withSingleMessage() }
        scenario(NavigateToChatScenario())
        ChatFragmentScreen {
            sendMessageButton.isClickable()
            Thread.sleep(4000)
        }
    }
}

private fun getIntent(): Intent {
    return Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
}