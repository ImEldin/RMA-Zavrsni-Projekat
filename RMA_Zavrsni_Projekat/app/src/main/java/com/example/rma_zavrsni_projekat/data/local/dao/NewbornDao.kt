package com.example.rma_zavrsni_projekat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rma_zavrsni_projekat.data.local.entity.NewbornEntity

@Dao
interface NewbornDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newborns: List<NewbornEntity>)

    @Query("SELECT * FROM newborns")
    suspend fun getAll(): List<NewbornEntity>

    @Query("DELETE FROM newborns")
    suspend fun clear()
}