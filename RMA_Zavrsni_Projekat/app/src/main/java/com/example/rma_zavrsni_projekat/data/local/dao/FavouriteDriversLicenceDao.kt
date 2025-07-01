package com.example.rma_zavrsni_projekat.data.local.dao

import androidx.room.*
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteDriversLicence
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDriversLicenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavouriteDriversLicence)

    @Delete
    suspend fun delete(fav: FavouriteDriversLicence)

    @Query("SELECT * FROM favourite_drivers_licences")
    fun getAll(): Flow<List<FavouriteDriversLicence>>
}
