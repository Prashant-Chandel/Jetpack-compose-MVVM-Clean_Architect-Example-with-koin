package com.clean.mvvm.domain.usecase.catsDetail

import com.clean.mvvm.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow

class CheckFavouriteUseCaseImpl(private val catDetailsRepo: CatDetailsRepository) :
    CheckFavUseCase {
    override suspend fun execute(imageId: String): Flow<Int?> = catDetailsRepo.isFavourite(imageId)
}

