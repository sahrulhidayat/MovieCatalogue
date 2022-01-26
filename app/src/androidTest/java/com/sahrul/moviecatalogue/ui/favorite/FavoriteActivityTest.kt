package com.sahrul.moviecatalogue.ui.favorite

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickBack
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.interaction.BaristaMenuClickInteractions.clickMenu
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.ui.home.HomeActivity
import com.sahrul.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class FavoriteActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun addAndDeleteFavoriteMovie() {
        clickListItem(R.id.rvMovie, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        clickMenu(R.id.favorite)
        assertListNotEmpty(R.id.rvFavoriteMovie)
        assertDisplayed(R.id.rvFavoriteMovie)
        clickListItem(R.id.rvFavoriteMovie, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        assertNotDisplayed(R.id.rvFavoriteMovie)
        assertDisplayed(R.id.animDataEmpty)
    }

    @Test
    fun addAndDeleteTvShow() {
        clickOn(R.string.tv_show)
        clickListItem(R.id.rvTvShow, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        clickMenu(R.id.favorite)
        clickOn(R.string.tv_show)
        assertListNotEmpty(R.id.rvFavoriteTvShow)
        assertDisplayed(R.id.rvFavoriteTvShow)
        clickListItem(R.id.rvFavoriteTvShow, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        assertNotDisplayed(R.id.rvFavoriteTvShow)
        assertDisplayed(R.id.animDataEmpty)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}