package com.example.rma_zavrsni_projekat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rma_zavrsni_projekat.data.local.entity.DriversLicenceEntity

@Dao
interface DriversLicenceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(licences: List<DriversLicenceEntity>)

    @Query("SELECT * FROM drivers_licences")
    suspend fun getAll(): List<DriversLicenceEntity>

    @Query("DELETE FROM drivers_licences")
    suspend fun clear()
}