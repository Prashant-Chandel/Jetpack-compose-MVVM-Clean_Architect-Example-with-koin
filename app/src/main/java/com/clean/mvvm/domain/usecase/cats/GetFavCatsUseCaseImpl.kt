package com.clean.mvvm.domain.usecase.cats

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.domain.mappers.CatDataModel
import com.clean.mvvm.domain.mappers.mapFavCatsDataItems
import com.clean.mvvm.domain.repositories.CatsRepository
import com.clean.mvvm.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavCatsUseCaseImpl(private val catRepo: CatsRepository) : GetFavCatsUseCase {
    override suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>> = flow {
        catRepo.fetchFavouriteCats(Constants.SUB_ID).collect { favCat ->
            when (favCat) {
                is NetworkResult.Success -> {
                    val catDataList = favCat.data?.map { cat ->
                        cat.mapFavCatsDataItems()
                    }
                    emit(NetworkResult.Success(catDataList))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(favCat.message))
                }
                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}

