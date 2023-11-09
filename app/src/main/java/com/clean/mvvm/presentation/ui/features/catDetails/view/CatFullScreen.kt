package com.clean.mvvm.presentation.ui.features.catDetails.view

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clean.mvvm.R
import com.clean.mvvm.di.url
import com.clean.mvvm.presentation.ui.theme.ComposeSampleTheme
import com.clean.mvvm.presentation.ui.theme.favourite
import com.clean.mvvm.utils.TestTags
import com.clean.mvvm.utils.TestTags.BACK_NAVIGATION_ICON_DESCRIPTION
import com.clean.mvvm.utils.TestTags.FULL_SCREEN_APP_BAR
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatFullDetail(
    url: String?,
    isFavourite: Boolean,
    onBackPressed: () -> Unit,
    favSelection: (isFav: Boolean) -> Unit
) {
    // on below line we are specifying theme as scaffold.
    Scaffold( // in scaffold we are specifying top bar.
        topBar = { CatFullScreenAppBar(onBackPressed) }) {
        Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            CatDetails(url, isFavourite, favSelection)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatFullScreenAppBar(onBackPressed: () -> Unit) {

    // inside top bar we are specifying background color.
    TopAppBar(
        colors = centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.colorPrimary),
            titleContentColor = Color(R.color.colorPrimary),
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = BACK_NAVIGATION_ICON_DESCRIPTION
                )
            }
        },
        title = {
            Text(
                // text to display in top app bar.
                text = stringResource(R.string.cat_image),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                // modifier to fill max width.
                modifier = Modifier
                    .testTag(stringResource(R.string.cat_image))
                    .fillMaxWidth(),
                // specifying text alignment.
                textAlign = TextAlign.Center,
                // specifying color for our text.
                color = Color.White
            )
        }, modifier = Modifier.testTag(FULL_SCREEN_APP_BAR)
    )
}

// Calling this function as content in the above function
@Composable
fun CatDetails(url: String?, isFavourite: Boolean, favSelection: (isFav: Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(top = 1.dp),
    ) {

        var scale by remember { mutableFloatStateOf(1f) }
        var offset by remember { mutableStateOf(Offset(0f, 0f)) }
        val maxZoom = 4f
        url?.let {
            GlideImage(
                imageModel = it,
                modifier = Modifier
                    .semantics { testTag = TestTags.FULL_IMG_DESCRIPTION }
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale *= zoom
                            scale = scale.coerceIn(1f, maxZoom)

                            offset = if (scale == 1f) {
                                Offset(0f, 0f)
                            } else {
                                val newOffsetX = offset.x + pan.x * scale
                                val newOffsetY = offset.y + pan.y * scale

                                Offset(newOffsetX, newOffsetY)
                            }
                        }
                    }
                    .graphicsLayer(
                        scaleX = maxOf(1f, minOf(maxZoom, scale)),
                        scaleY = maxOf(1f, minOf(maxZoom, scale)),
                        translationX = maxOf(-maxZoom, minOf(maxZoom, offset.x)),
                        translationY = maxOf(-maxZoom, minOf(maxZoom, offset.y))
                    )
                    .scrollable(
                        orientation = Orientation.Horizontal,
                        state = rememberScrollState()
                    ),
                // shows a progress indicator when loading an image.

                circularReveal = CircularReveal(duration = 100),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colorScheme.background,
                    highlightColor = Color.Gray,
                    durationMillis = 500,
                    dropOff = 0.55f,
                    tilt = 20f
                ),
                contentDescription = TestTags.FULL_IMG_DESCRIPTION
            )
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .align(Alignment.BottomCenter),
        ) {
            FavoriteButton(
                isFavourite = isFavourite,
                favSelection = favSelection,
                modifier = Modifier.semantics { testTag = TestTags.TOGGLE_FAV_BUTTON })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CatDetailPreview() {
    ComposeSampleTheme {
        CatFullDetail(url, false, { }, { })
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = favourite,
    isFavourite: Boolean,
    favSelection: (isFav: Boolean) -> Unit = {}
) {

    var isFavorite by remember { mutableStateOf(isFavourite) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            favSelection(it)
        }
    ) {

        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.5f
                scaleY = 1.5f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = stringResource(R.string.favourite_icon)
        )
    }

}

