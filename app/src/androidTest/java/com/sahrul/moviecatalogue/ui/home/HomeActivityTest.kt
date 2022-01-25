package com.sahrul.moviecatalogue.ui.home

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.Matcher
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

    @Test
    fun errorMovie() {
        onView(withId(R.id.rvMovie)).perform(setVisibility(false))
        onView(withId(R.id.animError)).perform(setVisibility(true))
        assertNotDisplayed(R.id.rvMovie)
        assertDisplayed(R.id.animError)
    }

    @Test
    fun errorTvShow() {
        clickOn(R.string.tv_show)
        onView(withId(R.id.rvTvShow)).perform(setVisibility(false))
        onView(withId(R.id.animError)).perform(setVisibility(true))
        assertNotDisplayed(R.id.rvTvShow)
        assertDisplayed(R.id.animError)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    private fun setVisibility(value: Boolean): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "Show / Hide View"
            }

            override fun perform(uiController: UiController?, view: View?) {
                view?.visibility = if (value) View.VISIBLE else View.GONE
            }
        }
    }
}