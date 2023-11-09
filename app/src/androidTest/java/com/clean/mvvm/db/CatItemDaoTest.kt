package com.clean.mvvm.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.clean.mvvm.data.database.LBGDatabase
import com.clean.mvvm.data.database.dao.FavouriteDao
import com.clean.mvvm.data.database.entities.FavImageEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class CatItemDaoTest {


    private lateinit var catFavDao: FavouriteDao
    private lateinit var itemDb: LBGDatabase

    @Before
    fun create() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        itemDb = Room
            .inMemoryDatabaseBuilder(context, LBGDatabase::class.java)
            .build()
        catFavDao = itemDb.favImageDao()
    }

    @After
    fun cleanup() {
        itemDb.close()
    }

    private fun getEntityData(id: Int): FavImageEntity {
        return FavImageEntity(favouriteId = id, imageId = "img$id")
    }

    @Test
    fun addCat_shouldReturn_theCat() = runTest {
        val item1 = getEntityData(1)
        val item2 = getEntityData(2)


        // Insert the FavImageEntity into the database
        val insertedId = catFavDao.insertFavCatImageRelation(item1)
        val insertedId2 = catFavDao.insertFavCatImageRelation(item2)

        // Verify that the insertion was successful
        assert(insertedId > 0)
        assert(insertedId2 > 0)

        // Retrieve the favouriteId using the imageId
        val retrievedFavId = catFavDao.getFavId("img1")
        val retrievedFavId2 = catFavDao.getFavId("img2")

        // Verify that the retrieved favouriteId matches the inserted one
        assert(retrievedFavId == 1)
        assert(retrievedFavId2 == 2)
    }

    @Test
    fun insertFavCatImageRelationListTest() = runTest {
        // Create a list of sample FavImageEntity objects
        val favImageEntities = listOf(
            getEntityData(3),
            getEntityData(4)
        )

        // Insert the list into the database
        val count = catFavDao.insertFavCatImageRelation(favImageEntities).size

        // Verify that the insertion was successful by checking the count of records
        assert(count == favImageEntities.size)
    }

    @Test
    fun deletedItem_shouldNot_be_present() = runTest {
        val item1 = getEntityData(1)
        val item2 = getEntityData(2)
        catFavDao.insertFavCatImageRelation(item2)
        // Delete the FavImageEntity using its imageId
        val count = catFavDao.deleteFavImage("img2")
        // Verify that the deletion was successful by checking the number of records deleted
        assert(count > 0)

    }

    @Test
    fun updateItem_shouldReturn_theItem() = runTest {
        // Create a list of sample FavImageEntity objects
        val favImageEntities = listOf(
            getEntityData(3),
            getEntityData(4)
        )

        // Insert the list into the database
        catFavDao.insertFavCatImageRelation(favImageEntities).size
        // Clear the table
        val clearedCount = catFavDao.clearTable()

        // Verify that the table was cleared by checking the number of records removed
        assert(clearedCount > 0)
    }
}