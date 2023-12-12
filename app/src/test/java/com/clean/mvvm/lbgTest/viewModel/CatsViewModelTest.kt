package com.clean.mvvm.lbgTest.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.clean.mvvm.R
import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.database.LBGDatabase
import com.clean.mvvm.data.models.catData.CatResponse
import com.clean.mvvm.data.models.catData.FavouriteCatsItem
import com.clean.mvvm.data.repositories.CatsRepositoryImpl
import com.clean.mvvm.data.services.CatsService
import com.clean.mvvm.data.services.cats.CatApiServiceHelperImpl
import com.clean.mvvm.data.services.cats.CatsDatabaseHelperImpl
import com.clean.mvvm.domain.repositories.CatsRepository
import com.clean.mvvm.domain.usecase.cats.GetCatsUseCaseImpl
import com.clean.mvvm.domain.usecase.cats.GetFavCatsUseCaseImpl
import com.clean.mvvm.models.catMocks.MockFavouriteCatsResponse
import com.clean.mvvm.models.catMocks.MocksCatsDataModel
import com.clean.mvvm.models.catMocks.toResponseApiCats
import com.clean.mvvm.models.catMocks.toResponseApiFavCats
import com.clean.mvvm.models.catMocks.toResponseCats
import com.clean.mvvm.models.catMocks.toResponseFavCats
import com.clean.mvvm.presentation.ui.features.cats.viewModel.CatsViewModel
import com.clean.mvvm.utils.Constants
import com.clean.mvvm.utils.TestTags
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.junit.JUnitAsserter.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatsViewModelTest {
    private lateinit var mCatsRepo: CatsRepository
    private val application: Application = mock()
    private lateinit var mViewModel: CatsViewModel

    @get:Rule
    val testInstantTaskExecutorRules: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catService: CatsService

    private val testDispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val databaseReference = mock(LBGDatabase::class.java)
        val apiHelper = CatApiServiceHelperImpl(catService)
        val dbHelper = CatsDatabaseHelperImpl(databaseReference)
        mCatsRepo = CatsRepositoryImpl(apiHelper, dbHelper)
        Dispatchers.setMain(testDispatcher)
        val catUseCase = GetCatsUseCaseImpl(mCatsRepo)
        val favCatUseCase = GetFavCatsUseCaseImpl(mCatsRepo)

        mViewModel = CatsViewModel(catUseCase, favCatUseCase)
    }


    @Test
    fun testGetEmptyData() = runTest(UnconfinedTestDispatcher()) {
        val expectedRepositories = Response.success(listOf<CatResponse>())
        // Mock the API response
        `when`(catService.fetchCatsImages(0)).thenReturn(expectedRepositories)
        // Call the method under test
        val result = catService.fetchCatsImages(0)
        // Verify that the API method is called with the correct username
        verify(catService).fetchCatsImages(0)
        // Verify that the result matches the expected repositories
        assert(result == expectedRepositories)
    }

    @Test
    fun testGetCatsApiData() = runTest(UnconfinedTestDispatcher()) {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val mockCatsData = MocksCatsDataModel()
        val response = toResponseApiCats(mockCatsData)
        val verifyData = toResponseCats(mockCatsData)
        `when`(catService.fetchCatsImages(10)).thenReturn(response)// Mock the API response
        verify(catService).fetchCatsImages(10)
        mViewModel.getCatsData()
        testDispatcher.scheduler.advanceUntilIdle() // Let the coroutine complete and changes propagate
        val result = mViewModel.state.value.cats
        assertEquals(
            application.getString(R.string.both_are_not_equal),
            result.size,
            verifyData.size
        )
        assertEquals(
            application.getString(R.string.both_are_not_equal),
            result[0].url,
            verifyData[0].url
        )
    }

    @Test
    fun testGetFavEmptyData() = runTest(UnconfinedTestDispatcher()) {
        val expectedRepositories = Response.success(listOf<FavouriteCatsItem>())
        // Mock the API response
        `when`(catService.fetchFavouriteCats("0")).thenReturn(expectedRepositories)
        // Call the method under test
        val result = catService.fetchFavouriteCats("0")
        // Verify that the API method is called with the correct username
        verify(catService).fetchFavouriteCats("0")
        // Verify that the result matches the expected repositories
        assert(result == expectedRepositories)
    }


    @Test
    fun testFetchFavouriteCatsSuccessState() = runTest(UnconfinedTestDispatcher()) {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val mockCatsData = MockFavouriteCatsResponse()
        val apiResponse = toResponseApiFavCats(mockCatsData)
        val verifyData = toResponseFavCats(mockCatsData)

        whenever(catService.fetchFavouriteCats(Constants.SUB_ID)).thenReturn(apiResponse)

        // Act
        val result = mCatsRepo.fetchTestFavouriteCats(Constants.SUB_ID).toList()

        // Assert
        assert(result[1] is NetworkResult.Success)
        assertEquals(
            application.getString(R.string.both_are_not_equal),
            result[1].data?.size, verifyData.data?.size
        )
        assertEquals(
            application.getString(R.string.both_are_not_equal),
            result[1].data?.get(0)?.image?.url, verifyData.data?.get(0)?.url
        )
    }

    @Test
    fun testFetchFavouriteCatsErrorState() = runTest(UnconfinedTestDispatcher()) {

        // Define a sample error response for the service
        val errorResponse = Response.error<List<FavouriteCatsItem>>(400, "Error message".toResponseBody(
            "application/json".toMediaType()
        ))
        // Set up the mock to return the error response
        `when`(catService.fetchFavouriteCats(TestTags.SUB_ID)).thenReturn(errorResponse)
        val result = mCatsRepo.fetchFavouriteCats(TestTags.SUB_ID).toList()
        verify(catService).fetchFavouriteCats(TestTags.SUB_ID)
        assert(result[1] is NetworkResult.Error)
        val errorResult = result[1] as NetworkResult.Error
    }

    @Test
    fun testFetchFavouriteCatsException() = runTest(UnconfinedTestDispatcher()) {
        // Set up the mock to throw an exception
        `when`(catService.fetchFavouriteCats(TestTags.SUB_ID)).thenThrow(RuntimeException("An error occurred"))
        val result = mCatsRepo.fetchFavouriteCats(TestTags.SUB_ID).toList()
        verify(catService).fetchFavouriteCats(TestTags.SUB_ID)
        assert(result[1] is NetworkResult.Error)
        val errorResult = result[1] as NetworkResult.Error
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }


}