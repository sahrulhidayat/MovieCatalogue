package com.sahrul.moviecatalogue.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
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
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView((withId(R.id.rvMovie))).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView((withId(R.id.rvTvShow))).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun emptyMovie() {
        onView(withId(R.id.rvMovie)).perform(setVisibility(false))
        onView(withId(R.id.animDataEmpty)).perform(setVisibility(true))
        onView(withId(R.id.rvMovie)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.animDataEmpty)).check(matches(isDisplayed()))
    }

    @Test
    fun emptyTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rvTvShow)).perform(setVisibility(false))
        onView(withId(R.id.animDataEmpty)).perform(setVisibility(true))
        onView(withId(R.id.rvTvShow)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.animDataEmpty)).check(matches(isDisplayed()))
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