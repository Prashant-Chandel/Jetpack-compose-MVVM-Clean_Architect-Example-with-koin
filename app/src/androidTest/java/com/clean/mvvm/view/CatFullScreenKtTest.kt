package com.clean.mvvm.view

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clean.mvvm.presentation.ui.features.catDetails.view.CatDetails
import com.clean.mvvm.presentation.ui.features.catDetails.view.CatFullScreenAppBar
import com.clean.mvvm.presentation.ui.features.catDetails.view.FavoriteButton
import com.clean.mvvm.utils.TestTags
import com.clean.mvvm.utils.TestTags.BACK_NAVIGATION_ICON_DESCRIPTION
import com.clean.mvvm.utils.TestTags.FULL_SCREEN_APP_BAR
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatFullScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()
    private lateinit var url: String

    @Before
    fun setUp() {
        url = TestTags.TEST_IMAGE_URL
    }

    @Test
    fun testCatDetails() {
        composeTestRule.setContent {
            CatDetails(
                url = url,
                isFavourite = true,
                favSelection = { isFav ->
                    // Mock the favSelection behavior

                }
            )
        }
        //Assert that the CatDetails composable is on the screen using the correct tag
        composeTestRule.onNodeWithTag(TestTags.FULL_IMG_DESCRIPTION, useUnmergedTree = true)
            .assertExists()

        // Perform a click action on the FavoriteButton if it exists in your composable
        composeTestRule.onNodeWithTag(TestTags.TOGGLE_FAV_BUTTON, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.TOGGLE_FAV_BUTTON, useUnmergedTree = true)
            .performClick()

    }

    @Test
    fun testCatFullScreenAppBar() {
        var onBackPressedInvoked = false
        composeTestRule.setContent {
            CatFullScreenAppBar {
                onBackPressedInvoked = true
            }
        }

        // Find and assert the existence of the AppBar with a specific test tag.
        composeTestRule.onNodeWithTag(FULL_SCREEN_APP_BAR).assertExists()

        // Find the navigation icon (back button) and click it.
        composeTestRule.onNodeWithContentDescription(BACK_NAVIGATION_ICON_DESCRIPTION)
            .assertExists()
            .performClick()

        // Assert that the `onBackPressed` action was invoked.
        composeTestRule.runOnUiThread {
            assert(onBackPressedInvoked)
        }
    }

    @Test
    fun testFavoriteButtonToggle() {
        var isFavourite = false

        composeTestRule.setContent {
            Column {
                FavoriteButton(
                    modifier = Modifier.semantics { testTag = TestTags.TOGGLE_FAV_BUTTON },
                    isFavourite = isFavourite
                ) {
                    isFavourite = it
                }
            }
        }

        // Assert that the FavoriteButton composable is on the screen
        composeTestRule.onNodeWithTag(TestTags.TOGGLE_FAV_BUTTON, useUnmergedTree = true)
            .assertExists()

        // Click the FavoriteButton to toggle it
        composeTestRule.onNodeWithTag(TestTags.TOGGLE_FAV_BUTTON, useUnmergedTree = true)
            .performClick()

        // Assert that the callback was invoked and the isFavourite variable was updated
        assert(isFavourite)

        // Click the FavoriteButton again to toggle it back
        composeTestRule.onNodeWithTag(TestTags.TOGGLE_FAV_BUTTON, useUnmergedTree = true)
            .performClick()

        // Assert that the callback was invoked and the isFavourite variable was updated
        assert(!isFavourite)
    }
}
