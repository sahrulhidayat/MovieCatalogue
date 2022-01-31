package com.sahrul.moviecatalogue.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.adevinta.android.barista.interaction.BaristaClickInteractions
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeErrorTest {

    @Before
    fun setup() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun errorMovie() {
        BaristaVisibilityAssertions.assertNotDisplayed(R.id.rvMovie)
        BaristaVisibilityAssertions.assertDisplayed(R.id.animError)
    }

    @Test
    fun errorTvShow() {
        BaristaClickInteractions.clickOn(R.string.tv_show)
        BaristaVisibilityAssertions.assertNotDisplayed(R.id.rvTvShow)
        BaristaVisibilityAssertions.assertDisplayed(R.id.animError)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi enable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data enable")
    }
}