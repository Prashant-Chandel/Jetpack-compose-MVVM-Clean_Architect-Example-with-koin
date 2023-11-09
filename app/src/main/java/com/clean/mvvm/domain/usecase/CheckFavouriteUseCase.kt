package com.clean.mvvm.domain.usecase

import com.clean.mvvm.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow

class CheckFavouriteUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId:String): Flow<Int?> = catDetailsRepo.isFavourite(imageId)
}

