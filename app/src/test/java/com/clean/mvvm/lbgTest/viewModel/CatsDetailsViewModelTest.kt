package com.clean.mvvm.lbgTest.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.clean.mvvm.data.database.LBGDatabase
import com.clean.mvvm.data.models.SuccessResponse
import com.clean.mvvm.data.models.catsMock.MockPostFavCatModel
import com.clean.mvvm.data.models.catsMock.MockSuccessResponse
import com.clean.mvvm.data.models.catsMock.toRequestPostFavCatData
import com.clean.mvvm.data.models.catsMock.toResponsePostSuccess
import com.clean.mvvm.data.repositories.CatsRepositoryImpl
import com.clean.mvvm.data.services.CatsService
import com.clean.mvvm.domain.usecase.GetCatsUseCase
import com.clean.mvvm.domain.usecase.GetFavCatsUseCase
import com.clean.mvvm.presentation.ui.features.cats.viewModel.CatsViewModel
import com.clean.mvvm.utils.TestTags
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatsDetailsViewModelTest {
    private lateinit var mViewModel: CatsViewModel

    @get:Rule
    val testInstantTaskExecuterRules: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catService: CatsService

    private val testDispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val databaseReference = mock(LBGDatabase::class.java)
        val mCatsRepo = CatsRepositoryImpl(catService, databaseReference)
        Dispatchers.setMain(testDispatcher)
        val catUseCase = GetCatsUseCase(mCatsRepo)
        val favCatUseCase = GetFavCatsUseCase(mCatsRepo)

        mViewModel = CatsViewModel(catUseCase, favCatUseCase)

    }

    @Test
    fun `test postFavCatData success`() = runTest(UnconfinedTestDispatcher()) {
        val postFavCatModel = toRequestPostFavCatData(MockPostFavCatModel())
        val expectedResponse = toResponsePostSuccess(MockSuccessResponse())
        Mockito.`when`(catService.postFavouriteCat(postFavCatModel)).thenReturn(expectedResponse)
        // Perform the actual request
        val response = catService.postFavouriteCat(postFavCatModel)
        // Assert the response
        assert(response.isSuccessful)
        assert(response.code() == 200)
        assert(response.body() == expectedResponse.body())
    }

    @Test
    fun `test deleteFavCat success`() = runTest(UnconfinedTestDispatcher()) {
        val expectedResponse =
            Response.success(SuccessResponse(0, message = "SUCCESS")) // HTTP status 204 for success
        Mockito.`when`(catService.deleteFavouriteCat(TestTags.FAV_ID)).thenReturn(expectedResponse)
        // Perform the actual request
        val response = catService.deleteFavouriteCat(TestTags.FAV_ID)
        // Assert the response
        assert(response.isSuccessful)
        assert(response.code() == 200) // HTTP status 204 indicates success
        assert(response.body() == expectedResponse.body())
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }


}