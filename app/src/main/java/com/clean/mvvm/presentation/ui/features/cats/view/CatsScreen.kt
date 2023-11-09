package com.clean.mvvm.presentation.ui.features.cats.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clean.mvvm.R
import com.clean.mvvm.data.models.mappers.CatDataModel
import com.clean.mvvm.presentation.contracts.BaseContract
import com.clean.mvvm.presentation.contracts.CatContract
import com.clean.mvvm.presentation.ui.components.EmptyView
import com.clean.mvvm.presentation.ui.features.cats.navigation.NavigationScreens
import com.clean.mvvm.presentation.ui.features.cats.navigation.getBottomNavigationItems
import com.clean.mvvm.presentation.ui.theme.Black80
import com.clean.mvvm.presentation.ui.theme.ComposeSampleTheme
import com.clean.mvvm.presentation.ui.theme.lightYellow
import com.clean.mvvm.utils.TestTags
import com.clean.mvvm.utils.TestTags.PROGRESS_BAR
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatScreen(
    state: CatContract.State,
    effectFlow: Flow<BaseContract.Effect>?,
    onNavigationRequested: (itemUrl: String, imageId: String) -> Unit,
    onRefreshCall: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val catMessage = stringResource(R.string.cats_are_loaded)
    //initializing the default selected item
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    /**
     * using the rememberNavController()
     * to get the instance of the navController
     */
    val navController = rememberNavController()
    // Listen for side effects from the VM
    LaunchedEffect(effectFlow) {
        effectFlow?.onEach { effect ->
            if (effect is BaseContract.Effect.DataWasLoaded)
                snackBarHostState.showSnackbar(
                    message = catMessage,
                    duration = SnackbarDuration.Short
                )
        }?.collect { value ->
            if (value is BaseContract.Effect.Error) {
                // Handle other emitted values if needed
                Toast.makeText(context, value.errorMessage, Toast.LENGTH_LONG).show()
            }

        }
    }
    Scaffold(
        topBar = {
            CatAppBar(
                showRefreshButton = navigationSelectedItem == 0,
                onNavigationIconClick = {
                    navigationSelectedItem =
                        0// Only show the button for the first segment {//top bar icon click call
                    navController.navigate(NavigationScreens.Home.screenRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, onRefreshCall = onRefreshCall
            )
        }, bottomBar = {
            NavigationBar(
                containerColor = colorResource(id = R.color.colorPrimary),
                contentColor = colorResource(id = R.color.white)
            ) {
                //getting the list of bottom navigation items for our data class
                getBottomNavigationItems(context).forEachIndexed { index, navigationItem ->
                    //iterating all items with their respective indexes
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(
                                text = navigationItem.title,
                                fontWeight=FontWeight.Bold,
                                color = colorResource(id = R.color.white)
                            )
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.title,
                                modifier = Modifier.semantics { testTag = navigationItem.title },
                                tint = if (index == navigationSelectedItem) colorResource(id = R.color.colorPrimary)
                                else colorResource(id = R.color.white)
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.screenRoute) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        //We need to setup our NavHost in here
        NavHost(
            navController = navController,
            startDestination = NavigationScreens.Home.screenRoute,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(NavigationScreens.Home.screenRoute) {
                UserView(
                    state,
                    false,
                    onNavigationRequested = onNavigationRequested
                )
            }
            composable(NavigationScreens.MyFavorites.screenRoute) {
                UserView(
                    state,
                    true,
                    onNavigationRequested = onNavigationRequested
                )
            }
        }
    }
}

@Composable
fun UserView(
    state: CatContract.State,
    isFavCatsCall: Boolean,
    onNavigationRequested: (itemUrl: String, imageId: String) -> Unit
) {
    Surface(modifier = Modifier.semantics {
        testTag = if (isFavCatsCall) TestTags.MY_FAVOURITE_SCREEN_TAG else TestTags.HOME_SCREEN_TAG
    }) {
        Box {
            val cats = if (isFavCatsCall) state.favCatsList else state.cats
            if (isFavCatsCall && cats.isEmpty()) {
                EmptyView(message = stringResource(R.string.favorite_screen_empty_list_text))
            } else
                CatsList(cats = cats, isLoading = state.isLoading,isFavCatsCall=isFavCatsCall) { itemUrl, imageId ->
                    onNavigationRequested(itemUrl, imageId)
                }
            if (state.isLoading)
                LoadingBar()
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatAppBar(
    showRefreshButton: Boolean,
    onNavigationIconClick: () -> Unit,
    onRefreshCall: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.semantics { testTag = TestTags.CAT_SCREEN_APP_BAR },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationIconClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    modifier = Modifier,
                    contentDescription = TestTags.ACTION_ICON
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = colorResource(id = R.color.white),
            )
        }, actions = {
            // Show the "Refresh" button only when showRefreshButton is true
            if (showRefreshButton) {
                IconButton(
                    onClick = onRefreshCall

                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = TestTags.REFRESH_ACTION
                    )
                }
            }
        },
        colors = centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.colorPrimary),
            titleContentColor = Color(R.color.white),
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Composable
fun CatsList(
    isLoading: Boolean = false,
    cats: List<CatDataModel>,
    isFavCatsCall: Boolean,
    onItemClicked: (url: String, imageId: String) -> Unit = { _: String, _: String -> },

) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        content = {
            this.items(cats) { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    border = BorderStroke(0.5.dp, Color.Gray),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 5.dp, end = 5.dp, top = 10.dp)
                        .clickable {
                            if (!isLoading) {
                                onItemClicked(item.url, item.imageId)
                            }
                        }
                        .semantics { testTag = TestTags.CAT_ITEM_TAG }
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(top = 1.dp),
                    ) {
                        ItemThumbnail(thumbnailUrl = item.url)
                        if (!isFavCatsCall && !item.name.isNullOrBlank()) {
                            Column(
                                modifier = Modifier
                                    .background(Black80)
                                    .fillMaxWidth()
                                    .align(Alignment.BottomStart),
                            ) {
                                Text(
                                    text = item.name,

                                    modifier=Modifier.padding(horizontal = 10.dp),
                                    color = colorResource(id = R.color.colorPrimary),
                                )
                                item.origin?.let {
                                    Text(
                                        text = it,
                                        modifier=Modifier.padding(horizontal = 10.dp),
                                        color = colorResource(id = R.color.colorPrimary),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }, modifier = Modifier
            .semantics { testTag = TestTags.CATS_LIST_TAG }
            .fillMaxSize()

    )
}

@Composable
fun ItemThumbnail(
    thumbnailUrl: String
) {
    GlideImage(
        imageModel = thumbnailUrl,
        modifier = Modifier
            .semantics { testTag = TestTags.LIST_IMG }
            .wrapContentSize()
            .wrapContentHeight()
            .fillMaxWidth(),
        // shows a progress indicator when loading an image.
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(duration = 100),
        shimmerParams = ShimmerParams(
            baseColor = MaterialTheme.colorScheme.background,
            highlightColor = Color.Gray,
            durationMillis = 500,
            dropOff = 0.55f,
            tilt = 20f
        ), contentDescription = TestTags.CAT_THUMBNAIL_PICTURE
    )
}

@Composable
fun LoadingBar() {
    Box(
        modifier = Modifier
            .semantics { testTag = TestTags.LOADING_BAR_TAG }
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center

    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .testTag(PROGRESS_BAR)
                .size(60.dp),
            color = colorResource(id = R.color.colorPrimary),
            strokeWidth = 5.dp, // Width of the progress indicator's stroke
            trackColor = lightYellow, // Color of the track behind the progress indicator
            strokeCap = StrokeCap.Round
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
        CatScreen(
            CatContract.State(),
            null,
            onNavigationRequested = { _: String, _: String -> },
            onRefreshCall = {})
    }
}