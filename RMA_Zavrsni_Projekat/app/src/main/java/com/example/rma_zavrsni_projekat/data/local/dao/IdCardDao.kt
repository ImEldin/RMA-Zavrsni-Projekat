package com.example.rma_zavrsni_projekat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rma_zavrsni_projekat.data.local.entity.IdCardEntity

@Dao
interface IdCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cards: List<IdCardEntity>)

    @Query("SELECT * FROM id_cards")
    suspend fun getAll(): List<IdCardEntity>

    @Query("DELETE FROM id_cards")
    suspend fun clear()
}