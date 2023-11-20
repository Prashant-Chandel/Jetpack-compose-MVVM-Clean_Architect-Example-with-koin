package com.clean.mvvm.data.repositories

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.database.LBGDatabase
import com.clean.mvvm.data.database.entities.FavImageEntity
import com.clean.mvvm.data.models.catData.CatResponse
import com.clean.mvvm.data.models.catData.FavouriteCatsItem
import com.clean.mvvm.data.services.CatsService
import com.clean.mvvm.domain.repositories.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CatsRepositoryImpl(private val catsService: CatsService, private val db: LBGDatabase) :
    CatsRepository {
    override suspend fun fetchCats(limit: Int) = flow<NetworkResult<List<CatResponse>>> {
        emit(NetworkResult.Loading())
        with(catsService.fetchCatsImages(limit)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }

    override suspend fun fetchFavouriteCats(subId: String) =
        flow<NetworkResult<List<FavouriteCatsItem>>> {
            emit(NetworkResult.Loading())
            with(catsService.fetchFavouriteCats(subId)) {
                if (isSuccessful) {
                    val storeInfo = this.body()?.map {
                        FavImageEntity(
                            favouriteId = it.id,
                            imageId = it.imageId
                        )
                    }
                    if (storeInfo != null) {
                        db.favImageDao().insertFavCatImageRelation(storeInfo)
                    }
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }

    override suspend fun fetchTestFavouriteCats(subId: String)=
        flow<NetworkResult<List<FavouriteCatsItem>>> {
            emit(NetworkResult.Loading())
            with(catsService.fetchFavouriteCats(subId)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }


}