package com.clean.mvvm.domain.repositories


import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.mappers.CatDataModel
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
     suspend fun  fetchCats(limit:Int=10): Flow<NetworkResult<List<CatDataModel>>>
     suspend fun fetchFavouriteCats(subId: String): Flow<NetworkResult<List<CatDataModel>>>
     suspend fun fetchTestFavouriteCats(subId: String): Flow<NetworkResult<List<CatDataModel>>>

}