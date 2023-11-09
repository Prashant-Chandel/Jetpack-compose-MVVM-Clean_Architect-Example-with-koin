package com.clean.mvvm.presentation.contracts

import com.clean.mvvm.data.models.mappers.CatDataModel

class CatContract  {
    data class State(
        val cats: List<CatDataModel> = listOf(),
        val favCatsList: List<CatDataModel> = listOf(),
        val isLoading: Boolean = false
    )
}
