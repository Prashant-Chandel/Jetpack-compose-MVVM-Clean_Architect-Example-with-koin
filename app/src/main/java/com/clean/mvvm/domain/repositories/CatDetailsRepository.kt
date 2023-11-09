package com.clean.mvvm.domain.repositories


import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.catDetails.PostFavCatModel
import com.clean.mvvm.data.models.mappers.CallSuccessModel
import kotlinx.coroutines.flow.Flow

interface CatDetailsRepository {
    suspend fun postFavouriteCat(favCat: PostFavCatModel): Flow<NetworkResult<CallSuccessModel>>
    suspend fun deleteFavouriteCat(
        imageId: String,
        favouriteId: Int
    ): Flow<NetworkResult<CallSuccessModel>>

    suspend fun isFavourite(imageId: String): Flow<Int?>

}