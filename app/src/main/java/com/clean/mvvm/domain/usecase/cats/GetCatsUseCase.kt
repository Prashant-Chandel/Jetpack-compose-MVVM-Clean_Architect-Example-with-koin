package com.clean.mvvm.domain.usecase.cats

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.domain.mappers.CatDataModel
import kotlinx.coroutines.flow.Flow

interface GetCatsUseCase {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>>
}