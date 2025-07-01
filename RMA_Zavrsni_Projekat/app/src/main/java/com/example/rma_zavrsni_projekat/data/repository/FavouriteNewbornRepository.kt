package com.example.rma_zavrsni_projekat.data.repository

import com.example.rma_zavrsni_projekat.data.local.dao.FavouriteNewbornDao
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteNewborn
import kotlinx.coroutines.flow.Flow

class FavouriteNewbornRepository(private val dao: FavouriteNewbornDao) {

    // Flow is a Kotlin construct representing a stream of data that you can observe over time
    fun getAllFavourites(): Flow<List<FavouriteNewborn>> = dao.getAll()

    suspend fun addToFavourites(fav: FavouriteNewborn) = dao.insert(fav)

    suspend fun removeFromFavourites(fav: FavouriteNewborn) = dao.delete(fav)
}