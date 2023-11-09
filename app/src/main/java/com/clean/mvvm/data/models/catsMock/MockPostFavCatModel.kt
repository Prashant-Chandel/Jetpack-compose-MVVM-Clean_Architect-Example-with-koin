package com.clean.mvvm.data.models.catsMock

import com.clean.mvvm.data.models.catDetails.PostFavCatModel
import com.google.gson.annotations.SerializedName
import com.clean.mvvm.utils.Constants

data class MockPostFavCatModel(
    @SerializedName("image_id")
    val imageId: String ="mi5",
    @SerializedName("sub_id")
    val subId: String= Constants.SUB_ID
)
fun toRequestPostFavCatData(mockPostFavCatModel: MockPostFavCatModel): PostFavCatModel {
    return PostFavCatModel(mockPostFavCatModel.imageId,mockPostFavCatModel.subId)
}