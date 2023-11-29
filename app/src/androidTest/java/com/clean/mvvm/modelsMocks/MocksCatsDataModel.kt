package com.clean.mvvm.modelsMocks

import com.clean.mvvm.data.models.catData.Breed
import com.clean.mvvm.data.models.catData.CatResponse
import com.clean.mvvm.domain.mappers.CatDataModel
import retrofit2.Response

data class MocksCatsDataModel(
    val breeds: List<Breed> = listOf(
        toResponseCatBread(MockBreed()),
        toResponseCatBread(MockBreed())
    ),
    val height: Int = 250,
    val id: String = "IDCat1",
    val url: String = "https://google.com/",
    val width: Int = 250
)

fun toResponseApiCats(mocksCatsDataModel: MocksCatsDataModel): Response<List<CatResponse>> {
    return Response.success(
        listOf(
            CatResponse(
                mocksCatsDataModel.breeds,
                mocksCatsDataModel.height,
                mocksCatsDataModel.id,
                mocksCatsDataModel.url,
                mocksCatsDataModel.width
            )
        )
    )
}

fun toResponseCats(mocksCatsDataModel: MocksCatsDataModel): List<CatDataModel> {
    return listOf(
        CatDataModel(
            imageId = mocksCatsDataModel.id,
            url = mocksCatsDataModel.url
        )
    )
}