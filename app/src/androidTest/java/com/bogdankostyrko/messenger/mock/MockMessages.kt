package com.bogdankostyrko.messenger.mock

import com.bogdankostyrko.messenger.utils.AssetsUtils.fromAssets
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.ok

class MockMessages(private val wireMockServer: WireMockServer) {

    private val matcher = WireMock.get(urlPattern)

    fun withSingleMessage() {
        wireMockServer.stubFor(matcher.willReturn(ok(fromAssets("messages/singleMessage.json"))))
    }

    companion object {

        val urlPattern = WireMock.urlPathMatching(".+messages")

        fun WireMockServer.messages(block: MockMessages.() -> Unit) {
            MockMessages(this).apply(block)
        }
    }
}