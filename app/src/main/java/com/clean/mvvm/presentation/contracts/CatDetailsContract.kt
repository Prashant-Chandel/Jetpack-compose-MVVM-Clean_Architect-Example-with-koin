package com.clean.mvvm.presentation.contracts


import com.clean.mvvm.data.models.mappers.CallSuccessModel


class CatDetailsContract {
    data class State(
        val postFavCatSuccess: CallSuccessModel?,
        val deleteFavCatSuccess: CallSuccessModel?
    )

}