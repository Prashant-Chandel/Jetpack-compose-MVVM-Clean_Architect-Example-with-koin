package com.clean.mvvm.domain.repositories


import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.catData.CatResponse
import com.clean.mvvm.data.models.catData.FavouriteCatsItem
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
     suspend fun fetchCats(limit: Int = 10): Flow<NetworkResult<List<CatResponse>>>
     suspend fun fetchFavouriteCats(subId: String): Flow<NetworkResult<List<FavouriteCatsItem>>>
     suspend fun fetchTestFavouriteCats(subId: String): Flow<NetworkResult<List<FavouriteCatsItem>>>

}