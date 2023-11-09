package com.clean.mvvm.data.models.catsMock

import com.clean.mvvm.data.models.SuccessResponse
import retrofit2.Response

data class MockSuccessResponse(
    val id: Int=123456,
    val message: String="User Favourite data posted successfully"
)

fun toResponsePostSuccess(mockSuccessResponse: MockSuccessResponse): Response<SuccessResponse> {
    return Response.success(SuccessResponse(mockSuccessResponse.id,mockSuccessResponse.message))
}