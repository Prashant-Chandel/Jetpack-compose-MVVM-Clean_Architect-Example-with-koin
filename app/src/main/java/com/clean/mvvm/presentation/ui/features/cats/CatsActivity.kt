package com.clean.mvvm.presentation.ui.features.cats

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityOptionsCompat
import com.clean.mvvm.presentation.ui.features.catDetails.CatFullImageActivity
import com.clean.mvvm.presentation.ui.features.cats.view.CatScreen
import com.clean.mvvm.presentation.ui.features.cats.viewModel.CatsViewModel
import com.clean.mvvm.presentation.ui.theme.ComposeSampleTheme
import com.clean.mvvm.utils.Constants
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatsActivity : ComponentActivity() {
    private val viewModel: CatsViewModel by viewModel()
    private val myActivityResultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.getFavCatsData()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                CatsDestination()
            }
        }
    }


    @Composable
    fun CatsDestination() {
        CatScreen(
            state = viewModel.state.collectAsState().value,
            effectFlow = viewModel.effects.receiveAsFlow(),
            onNavigationRequested = { itemUrl, imageId ->
                myActivityResultContract.launch(
                    Intent(
                        this@CatsActivity,
                        CatFullImageActivity::class.java
                    ).apply {
                        putExtra(
                            Constants.URL,
                            itemUrl
                        )
                        putExtra(Constants.IMAGE_ID, imageId)
                    }, ActivityOptionsCompat.makeSceneTransitionAnimation(this)
                )

            },
            onRefreshCall = {
                viewModel.getCatsData()

            })
    }


}