package com.clean.mvvm.domain.usecase

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.mappers.CallSuccessModel
import com.clean.mvvm.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow

class DeleteFavCatUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId: String, favId: Int): Flow<NetworkResult<CallSuccessModel>> =
        catDetailsRepo.deleteFavouriteCat(imageId, favId)
}

