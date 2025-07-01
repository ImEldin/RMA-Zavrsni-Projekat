package com.example.rma_zavrsni_projekat.data.repository

import com.example.rma_zavrsni_projekat.data.local.dao.FavouriteDriversLicenceDao
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteDriversLicence
import kotlinx.coroutines.flow.Flow

class FavouriteDriversLicenceRepository(private val dao: FavouriteDriversLicenceDao) {

    fun getAllFavourites(): Flow<List<FavouriteDriversLicence>> = dao.getAll()

    suspend fun addToFavourites(fav: FavouriteDriversLicence) = dao.insert(fav)

    suspend fun removeFromFavourites(fav: FavouriteDriversLicence) = dao.delete(fav)
}