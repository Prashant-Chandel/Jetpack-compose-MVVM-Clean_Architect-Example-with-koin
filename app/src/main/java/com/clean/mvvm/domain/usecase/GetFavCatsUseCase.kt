package com.clean.mvvm.domain.usecase

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.mappers.CatDataModel
import com.clean.mvvm.domain.repositories.CatsRepository
import com.clean.mvvm.utils.Constants
import kotlinx.coroutines.flow.Flow

class GetFavCatsUseCase(private val catDetailsRepo: CatsRepository) {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>> {
        return catDetailsRepo.fetchFavouriteCats(
            Constants.SUB_ID
        )
    }
}

