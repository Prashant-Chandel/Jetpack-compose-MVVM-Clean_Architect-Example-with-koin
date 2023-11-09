package com.clean.mvvm.domain.usecase

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.mappers.CatDataModel
import com.clean.mvvm.domain.repositories.CatsRepository
import kotlinx.coroutines.flow.Flow

class GetCatsUseCase(private val catsRepo: CatsRepository) {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>> = catsRepo.fetchCats()
    suspend fun execute(subId:String): Flow<NetworkResult<List<CatDataModel>>> = catsRepo.fetchFavouriteCats(subId)
}

