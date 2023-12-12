package com.clean.mvvm.data.services.cats

import com.clean.mvvm.data.models.catData.FavouriteCatsItem

interface CatsDatabaseHelper {
    suspend fun insertFavCatImageRelation(favCatItems: List<FavouriteCatsItem>): List<Long>
}