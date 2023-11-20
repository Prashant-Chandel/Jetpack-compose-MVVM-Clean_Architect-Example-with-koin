package com.clean.mvvm.data.repositories

import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.database.LBGDatabase
import com.clean.mvvm.data.database.entities.FavImageEntity
import com.clean.mvvm.data.models.SuccessResponse
import com.clean.mvvm.data.models.catDetails.PostFavCatModel
import com.clean.mvvm.data.services.CatsService
import com.clean.mvvm.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class CatDetailsRepositoryImpl(private val catsService: CatsService, private val db: LBGDatabase) :
    CatDetailsRepository {


    override suspend fun postFavouriteCat(favCat: PostFavCatModel) =
        flow<NetworkResult<SuccessResponse>> {
            emit(NetworkResult.Loading())
            with(catsService.postFavouriteCat(favCat)) {
                if (isSuccessful) {
                    this.body()?.id?.let { FavImageEntity(it, favCat.imageId) }
                        ?.let { db.favImageDao().insertFavCatImageRelation(it) }
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }

    override suspend fun deleteFavouriteCat(imageId: String, favouriteId: Int) =
        flow<NetworkResult<SuccessResponse>> {
            emit(NetworkResult.Loading())
            with(catsService.deleteFavouriteCat(favouriteId)) {
                if (isSuccessful) {
                    db.favImageDao().deleteFavImage(imageId)
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }

        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }


    override suspend fun isFavourite(imageId: String) = flow {
        emit(db.favImageDao().getFavId(imageId))
    }


}



