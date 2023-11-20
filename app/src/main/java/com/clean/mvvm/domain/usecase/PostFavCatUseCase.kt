package com.clean.mvvm.domain.usecase

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.catDetails.PostFavCatModel
import com.clean.mvvm.domain.mappers.CallSuccessModel
import com.clean.mvvm.domain.mappers.mapSuccessData
import com.clean.mvvm.domain.repositories.CatDetailsRepository
import com.clean.mvvm.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostFavCatUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId: String): Flow<NetworkResult<CallSuccessModel>> = flow {
        val favCat = PostFavCatModel(imageId, Constants.SUB_ID)
        catDetailsRepo.postFavouriteCat(favCat).collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    emit(NetworkResult.Success(response.data?.mapSuccessData()))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(response.message))
                }

                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}

