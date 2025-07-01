package com.example.rma_zavrsni_projekat.data.repository

import com.example.rma_zavrsni_projekat.data.local.dao.FavouriteIdCardDao
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteIdCard
import kotlinx.coroutines.flow.Flow

class FavouriteIdCardRepository(private val dao: FavouriteIdCardDao) {

    fun getAllFavourites(): Flow<List<FavouriteIdCard>> = dao.getAll()

    suspend fun addToFavourites(fav: FavouriteIdCard) = dao.insert(fav)

    suspend fun removeFromFavourites(fav: FavouriteIdCard) = dao.delete(fav)
}