package com.clean.mvvm.data.services.catsDetail

import com.clean.mvvm.data.models.SuccessResponse
import com.clean.mvvm.data.models.catDetails.PostFavCatModel
import retrofit2.Response

interface CatDetailsApiServiceHelper {
    suspend fun postFavouriteCat(favCat: PostFavCatModel): Response<SuccessResponse>
    suspend fun deleteFavouriteCat(favouriteId: Int): Response<SuccessResponse>

}