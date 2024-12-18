package com.bogdankostyrko.messenger.scenarios

import com.bogdankostyrko.messenger.screens.ChannelsFragmentScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class NavigateToChatScenario : Scenario() {
    override val steps: TestContext<Unit>.() -> Unit = {
        step("Navigate to chat") {
            ChannelsFragmentScreen {
                channelsRecyclerView {
                    firstChild<ChannelsFragmentScreen.KStreamItem> {
                        streamTitle.click()
                    }
                }
            }
        }
    }
}