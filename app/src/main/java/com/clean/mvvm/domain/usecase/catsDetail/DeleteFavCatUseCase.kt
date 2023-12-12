package com.clean.mvvm.domain.usecase.catsDetail

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.domain.mappers.CallSuccessModel
import kotlinx.coroutines.flow.Flow

interface DeleteFavCatUseCase {
    suspend fun execute(imageId: String, favId: Int): Flow<NetworkResult<CallSuccessModel>>
}