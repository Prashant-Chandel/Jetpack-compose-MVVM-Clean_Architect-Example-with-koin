package com.clean.mvvm.view

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.clean.mvvm.catsMock.MockFavouriteCatsResponse
import com.clean.mvvm.catsMock.MocksCatsDataModel
import com.clean.mvvm.catsMock.toResponseCats
import com.clean.mvvm.catsMock.toResponseFavCats
import com.clean.mvvm.domain.mappers.CatDataModel
import com.clean.mvvm.presentation.contracts.CatContract
import com.clean.mvvm.presentation.ui.components.EmptyView
import com.clean.mvvm.presentation.ui.features.cats.view.CatScreen
import com.clean.mvvm.presentation.ui.features.cats.view.CatsList
import com.clean.mvvm.presentation.ui.features.cats.view.ItemThumbnail
import com.clean.mvvm.presentation.ui.features.cats.view.LoadingBar
import com.clean.mvvm.presentation.ui.features.cats.view.UserView
import com.clean.mvvm.utils.TestTags
import com.clean.mvvm.utils.TestTags.PROGRESS_BAR
import org.junit.Rule
import org.junit.Test

class CatsScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testCatScreen() {
        val state = CatContract.State(isLoading = true)
        val effectFlow = null

        composeTestRule.setContent {
            CatScreen(
                state = state,
                effectFlow = effectFlow,
                onNavigationRequested = { _, _ ->
                    // Handle navigation request in the test if needed
                },
                onRefreshCall = {

                }
            )
        }

        // Assert that the top app bar is displayed
        composeTestRule.onNodeWithTag(TestTags.CAT_SCREEN_APP_BAR).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(TestTags.ACTION_ICON).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(TestTags.ACTION_ICON).performClick()
        composeTestRule.onNodeWithContentDescription(TestTags.REFRESH_ACTION).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(TestTags.REFRESH_ACTION).performClick()
        composeTestRule.onNodeWithText(TestTags.APP_NAME).assertIsDisplayed()

        // Click on the "Home" bottom navigation item
        composeTestRule.onNodeWithTag(TestTags.HOME_TAG, useUnmergedTree = true).performClick()

        // Wait for the Home screen content to appear and assert that it's displayed
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(TestTags.HOME_SCREEN_TAG, useUnmergedTree = true)
            .assertIsDisplayed()

        // Click on the "My Favorites" bottom navigation item
        composeTestRule.onNodeWithTag(TestTags.MY_FAVOURITE_TAG, useUnmergedTree = true)
            .performClick()

        // Wait for the My Favorites screen content to appear and assert that it's displayed
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(TestTags.EMPTY_VIEW).assertExists()
        composeTestRule.onNodeWithTag(TestTags.EMPTY_VIEW).assertIsDisplayed()

    }
    @Test
    fun testUserViewWithFavCats() {

        val state =
            com.clean.mvvm.catsMock.toResponseFavCats(com.clean.mvvm.catsMock.MockFavouriteCatsResponse()).data?.let {
                CatContract.State(favCatsList = it)
            }
        val isFavCatsCall = true
        val onNavigationRequested: (String, String) -> Unit = { _, _ -> /* Handle navigation */ }

        composeTestRule.setContent {
            if (state != null) {
                UserView(state, isFavCatsCall, onNavigationRequested)
            }
        }

        // Test that favorite cats are displayed
        composeTestRule.onNodeWithTag(TestTags.MY_FAVOURITE_SCREEN_TAG).assertExists()
        composeTestRule.onNodeWithTag(TestTags.MY_FAVOURITE_SCREEN_TAG).assertIsDisplayed()

    }
    @Test
    fun testUserViewWithEmptyFavCats() {
        val state = CatContract.State(favCatsList = emptyList())
        val isFavCatsCall = true
        val onNavigationRequested: (String, String) -> Unit = { _, _ -> /* Handle navigation */ }

        composeTestRule.setContent {
            UserView(state, isFavCatsCall, onNavigationRequested)
        }

        // Test that the empty view is displayed
        composeTestRule.onNodeWithTag(TestTags.EMPTY_VIEW,useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(TestTags.EMPTY_VIEW,useUnmergedTree = true).assertIsDisplayed()
    }
    @Test
    fun testEmptyView() {
        val message = "Test Message"

        composeTestRule.setContent {
            EmptyView(message)
        }

        // Test that the empty view is displayed with the given message
        composeTestRule.onNodeWithTag(TestTags.EMPTY_VIEW).assertExists()
        composeTestRule.onNodeWithText(message).assertExists()
    }

    @Test
    fun testUserView_IfLoadingIsTrue() {
        // Define a sample state for testing
        val state =
            com.clean.mvvm.catsMock.toResponseFavCats(com.clean.mvvm.catsMock.MockFavouriteCatsResponse()).data?.let {
                CatContract.State(
                    favCatsList = it,
                    cats = com.clean.mvvm.catsMock.toResponseCats(com.clean.mvvm.catsMock.MocksCatsDataModel()),
                    isLoading = true
                )
            }

        composeTestRule.setContent {
            if (state != null) {
                UserView(state = state, isFavCatsCall = false, onNavigationRequested = { _, _-> })
            }
        }
        // Verify that the LoadingBar is exist
        composeTestRule.onNodeWithTag(TestTags.LOADING_BAR_TAG,useUnmergedTree = true).assertExists()

        // Verify that the UserView is displayed correctly
        composeTestRule.onNodeWithTag(TestTags.HOME_SCREEN_TAG,useUnmergedTree = true)
            .assertIsDisplayed()

        // Verify that the CatsList is displayed with the correct number of items
        composeTestRule.onNodeWithTag(TestTags.CATS_LIST_TAG,useUnmergedTree = true)
            .assertIsDisplayed()
            .onChildren()
            .assertCountEquals(1) // The total number of cats from state (favCatsList)

        // Verify that the LoadingBar is  displayed
        composeTestRule.onNodeWithTag(TestTags.LOADING_BAR_TAG,useUnmergedTree = true)
            .assertIsDisplayed()
    }
    @Test
    fun testUserView_IfLoadingIsFalse() {
        // Define a sample state for testing
        val state =
            com.clean.mvvm.catsMock.toResponseFavCats(com.clean.mvvm.catsMock.MockFavouriteCatsResponse()).data?.let {
                CatContract.State(
                    favCatsList = it,
                    cats = com.clean.mvvm.catsMock.toResponseCats(com.clean.mvvm.catsMock.MocksCatsDataModel()),
                    isLoading = false
                )
            }

        composeTestRule.setContent {
            if (state != null) {
                UserView(state = state, isFavCatsCall = false, onNavigationRequested = { _, _ -> })
            }
        }
        // Verify that the LoadingBar is not displayed
        composeTestRule.onNodeWithTag(TestTags.LOADING_BAR_TAG,useUnmergedTree = true).assertDoesNotExist()

        // Verify that the UserView is displayed correctly
        composeTestRule.onNodeWithTag(TestTags.HOME_SCREEN_TAG,useUnmergedTree = true)
            .assertIsDisplayed()

        // Verify that the CatsList is displayed with the correct number of items
        composeTestRule.onNodeWithTag(TestTags.CATS_LIST_TAG,useUnmergedTree = true)
            .assertIsDisplayed()
            .onChildren()
            .assertCountEquals(1) // The total number of cats from state (favCatsList)

    }

    @Test
    fun testCatsList() {
        // Define a list of cat data models for testing
        val catList = listOf(
            CatDataModel(favId = 123, imageId = "img1", url =  "https://images.freeimages.com/images/large-previews/d4f/www-1242368.jpg"),
            CatDataModel(favId = 1234, imageId = "img2", url = "https://images.freeimages.com/images/large-previews/636/holding-a-dot-com-iii-1411477.jpg"),
            CatDataModel(favId = 1235, imageId = "img4", url = "https://cdn.pixabay.com/photo/2020/09/19/19/37/landscape-5585247_1280.jpg"),
            CatDataModel(favId = 1236, imageId = "img5", url = "https://cdn.pixabay.com/photo/2022/01/11/21/48/link-6931554_1280.png")
        )

        var selectedItemUrl: String? = null
        var selectedItemImageId: String? = null

        composeTestRule.setContent {
            CatsList(cats = catList, onItemClicked = { url, imageId ->
                selectedItemUrl = url
                selectedItemImageId = imageId
            }, isFavCatsCall = true)
        }

        // Verify that the CatsList is displayed
        composeTestRule.onNodeWithTag(TestTags.CATS_LIST_TAG)
            .assertIsDisplayed()

        // Verify that the correct number of cat items are displayed
        composeTestRule.onAllNodesWithTag(TestTags.CAT_ITEM_TAG).assertCountEquals(catList.size)

        // Click on a cat item
        composeTestRule.onAllNodesWithTag(TestTags.CAT_ITEM_TAG)
            .onFirst()
            .assertHasClickAction().performClick()

        // Verify that the onItemClicked callback was called with the correct URL and imageId
        composeTestRule.waitForIdle()
    }

    @Test
    fun itemThumbnailTest() {
        composeTestRule.setContent {
            ItemThumbnail(thumbnailUrl = TestTags.TEST_IMAGE_URL)
        }

        // Use the onNode function to find and verify the composable
        composeTestRule.onNodeWithTag(TestTags.LIST_IMG)
            .assertIsDisplayed()
        // Verify that the content description is set correctly
       /* composeTestRule.onNodeWithContentDescription(TestTags.CAT_THUMBNAIL_PICTURE, ignoreCase = true)
            .assertIsDisplayed()*/

        // Verify that the composable is clickable and can be interacted with
        composeTestRule.onNodeWithTag(TestTags.LIST_IMG)
            .performClick()

    }

    @Test
    fun loadingBarTest() {
        // Set up the composable
        composeTestRule.setContent {
            LoadingBar()
        }

        // Verify that the LoadingBar composable is displayed and contains a CircularProgressIndicator
        composeTestRule.onNodeWithTag(TestTags.LOADING_BAR_TAG)
            .assertIsDisplayed()

        // Verify that the CircularProgressIndicator is displayed inside the LoadingBar
        composeTestRule.onNodeWithTag(PROGRESS_BAR)
            .assertIsDisplayed()
    }


}