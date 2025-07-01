package com.example.rma_zavrsni_projekat.data.local.dao

import androidx.room.*
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteNewborn
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteNewbornDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavouriteNewborn)

    @Delete
    suspend fun delete(fav: FavouriteNewborn)

    @Query("SELECT * FROM favourite_newborns")
    fun getAll(): Flow<List<FavouriteNewborn>>
}