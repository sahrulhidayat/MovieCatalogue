package com.sahrul.moviecatalogue.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions
import com.adevinta.android.barista.rule.cleardata.ClearDatabaseRule
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityErrorTest {

    @get:Rule
    var clearDatabaseRule = ClearDatabaseRule()

    @Before
    fun setup() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun errorMovie() {
        assertNotDisplayed(R.id.rvMovie)
        assertDisplayed(R.id.animError)
    }

    @Test
    fun errorTvShow() {
        BaristaClickInteractions.clickOn(R.string.tv_show)
        assertNotDisplayed(R.id.rvTvShow)
        assertDisplayed(R.id.animError)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi enable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data enable")
    }
}