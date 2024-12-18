package com.bogdankostyrko.messenger.utils

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AppTestRule<T : Activity>(
    startActivityIntent: Intent,
    private val configuration: Application.() -> Unit
) : TestRule {

    val wiremockRule = WireMockRule() // default: localhost:8080
    val activityScenarioRule = ActivityScenarioRule<T>(startActivityIntent)
    private val intentsRule = IntentsRule()

    override fun apply(base: Statement?, description: Description?): Statement {
        return RuleChain.outerRule(intentsRule)
            .around(wiremockRule)
            .around(activityScenarioRule)
            .apply { configuration(ApplicationProvider.getApplicationContext()) }
            .apply(base, description)
    }
}