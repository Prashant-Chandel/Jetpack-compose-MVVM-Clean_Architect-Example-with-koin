package com.clean.mvvm.data.database.dao

import androidx.room.*
import com.clean.mvvm.data.database.entities.FavImageEntity

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCatImageRelation(favImageEntity: FavImageEntity): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCatImageRelation(favImageEntity:List<FavImageEntity>): List<Long>
    @Query("SELECT favouriteId from favourite_details WHERE imageId=:imageId")
    suspend fun getFavId(imageId: String?): Int?

    @Query("DELETE FROM favourite_details where imageId=:imgId")
    suspend fun deleteFavImage(imgId: String): Int

    @Query("DELETE FROM favourite_details")
    suspend fun clearTable(): Int
}