package com.clean.mvvm.domain.usecase

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.catDetails.PostFavCatModel
import com.clean.mvvm.data.models.mappers.CallSuccessModel
import com.clean.mvvm.domain.repositories.CatDetailsRepository
import com.clean.mvvm.utils.Constants
import kotlinx.coroutines.flow.Flow

class PostFavCatUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId: String): Flow<NetworkResult<CallSuccessModel>> {
        val favCat = PostFavCatModel(imageId, Constants.SUB_ID)
        return catDetailsRepo.postFavouriteCat(favCat)
    }
}

