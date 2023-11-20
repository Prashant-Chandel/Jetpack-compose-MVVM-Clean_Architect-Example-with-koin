package com.clean.mvvm.domain.mappers

data class CatDataModel(
    val name: String? ="",
    val origin:String?="",
    val favId:Int=0,
    val imageId: String,
    val url: String,
)
