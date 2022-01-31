package com.sahrul.moviecatalogue.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        assertDisplayed(R.id.rvMovie)
        scrollListToPosition(R.id.rvMovie, 19)
    }

    @Test
    fun loadTvShows() {
        clickOn(R.string.tv_show)
        assertDisplayed(R.id.rvTvShow)
        scrollListToPosition(R.id.rvTvShow, 19)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}