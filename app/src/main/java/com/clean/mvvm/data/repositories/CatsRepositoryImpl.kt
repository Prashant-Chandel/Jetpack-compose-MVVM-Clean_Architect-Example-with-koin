package com.clean.mvvm.data.repositories

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.catData.CatResponse
import com.clean.mvvm.data.models.catData.FavouriteCatsItem
import com.clean.mvvm.data.services.cats.CatApiServiceHelper
import com.clean.mvvm.data.services.cats.CatsDatabaseHelper
import com.clean.mvvm.domain.repositories.CatsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CatsRepositoryImpl(
    private val catsApiService: CatApiServiceHelper,
    private val catsDatabaseHelper: CatsDatabaseHelper
) : CatsRepository {
    override suspend fun fetchCats(limit: Int) = flow<NetworkResult<List<CatResponse>>> {
        emit(NetworkResult.Loading())
        with(catsApiService.fetchCatsImages(limit)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

    override suspend fun fetchFavouriteCats(subId: String) =
        flow<NetworkResult<List<FavouriteCatsItem>>> {
            emit(NetworkResult.Loading())
            with(catsApiService.fetchFavouriteCats(subId)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                    this.body()?.let { catsDatabaseHelper.insertFavCatImageRelation(it) }
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }

    override suspend fun fetchTestFavouriteCats(subId: String) =
        flow<NetworkResult<List<FavouriteCatsItem>>> {
            emit(NetworkResult.Loading())
            with(catsApiService.fetchFavouriteCats(subId)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }


}