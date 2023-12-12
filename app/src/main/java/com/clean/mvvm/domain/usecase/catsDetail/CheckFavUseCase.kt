package com.clean.mvvm.domain.usecase.catsDetail

import kotlinx.coroutines.flow.Flow

interface CheckFavUseCase {
    suspend fun execute(imageId: String): Flow<Int?>
}