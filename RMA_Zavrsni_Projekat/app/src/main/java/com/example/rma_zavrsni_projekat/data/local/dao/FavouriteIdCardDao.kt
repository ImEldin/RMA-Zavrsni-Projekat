package com.example.rma_zavrsni_projekat.data.local.dao

import androidx.room.*
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteIdCard
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteIdCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavouriteIdCard)

    @Delete
    suspend fun delete(fav: FavouriteIdCard)

    @Query("SELECT * FROM favourite_id_cards")
    fun getAll(): Flow<List<FavouriteIdCard>>
}