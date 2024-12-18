package com.bogdankostyrko.messenger.presentation.screens.chat

import com.bogdankostyrko.messenger.presentation.elm.LceState
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class ChatReducerTest : BehaviorSpec({
    Given("ChatReducer") {
        val reducer = ChatReducer()
        When("reduce") {
            And("state is initial") {
                And("event is LoadMessages") {
                    val streamName = "general"
                    val actual = reducer.reduce(
                        event = ChatEvent.Ui.LoadMessages(streamName = streamName),
                        state = ChatState(LceState.Content(emptyList()))
                    )
                    val expectedStatus = LceState.Loading
                    val expectedCommand = ChatCommand.LoadMessages(streamName = streamName)
                    Then("should return result with status is Loading and command contain LoadMessages") {
                        actual.state.messages shouldBe expectedStatus
                        actual.commands shouldContain expectedCommand
                    }
                }
            }
        }
    }
})