package com.sahrul.moviecatalogue.ui.detail

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.adevinta.android.barista.assertion.BaristaContentDescriptionAssertions.assertContentDescription
import com.adevinta.android.barista.assertion.BaristaImageViewAssertions.assertHasAnyDrawable
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.ui.home.HomeActivity
import com.sahrul.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadDetailMovie() {
        clickListItem(R.id.rvMovie, 0)
        assertHasAnyDrawable(R.id.imgPoster)
        assertContentDescription(R.id.imgPoster, R.string.image_poster)
        assertDisplayed(R.id.tvTitle)
        assertDisplayed(R.id.tvRatings)
        assertDisplayed(R.id.tvCategoryTitle)
        assertDisplayed(R.id.tvCategory)
        assertDisplayed(R.id.tvReleaseDateTitle)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvDurationTitle)
        assertDisplayed(R.id.tvDuration)
        assertDisplayed(R.id.tvOverviewTitle)
        assertDisplayed(R.id.tvOverview)
        assertDisplayed(R.id.fabFavorite)
        assertHasAnyDrawable(R.id.fabFavorite)
    }

    @Test
    fun loadDetailTvShow() {
        clickOn(R.string.tv_show)
        clickListItem(R.id.rvTvShow, 0)
        assertHasAnyDrawable(R.id.imgPoster)
        assertContentDescription(R.id.imgPoster, R.string.image_poster)
        assertDisplayed(R.id.tvTitle)
        assertDisplayed(R.id.tvRatings)
        assertDisplayed(R.id.tvCategoryTitle)
        assertDisplayed(R.id.tvCategory)
        assertDisplayed(R.id.tvReleaseDateTitle)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvDurationTitle)
        assertDisplayed(R.id.tvDuration)
        assertDisplayed(R.id.tvOverviewTitle)
        assertDisplayed(R.id.tvOverview)
        assertDisplayed(R.id.fabFavorite)
        assertHasAnyDrawable(R.id.fabFavorite)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}