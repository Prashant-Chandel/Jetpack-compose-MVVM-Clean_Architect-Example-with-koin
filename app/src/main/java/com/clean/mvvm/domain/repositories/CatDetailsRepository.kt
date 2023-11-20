package com.clean.mvvm.domain.repositories


import com.clean.mvvm.data.NetworkResult
import com.clean.mvvm.data.models.SuccessResponse
import com.clean.mvvm.data.models.catDetails.PostFavCatModel
import kotlinx.coroutines.flow.Flow

interface CatDetailsRepository {
    suspend fun postFavouriteCat(favCat: PostFavCatModel): Flow<NetworkResult<SuccessResponse>>
    suspend fun deleteFavouriteCat(
        imageId: String,
        favouriteId: Int
    ): Flow<NetworkResult<SuccessResponse>>

    suspend fun isFavourite(imageId: String): Flow<Int?>

}