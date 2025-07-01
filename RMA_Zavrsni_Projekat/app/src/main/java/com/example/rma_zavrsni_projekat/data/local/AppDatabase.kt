package com.example.rma_zavrsni_projekat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rma_zavrsni_projekat.data.local.dao.*
import com.example.rma_zavrsni_projekat.data.local.entity.*

@Database(
    entities = [
        NewbornEntity::class,
        IdCardEntity::class,
        DriversLicenceEntity::class,
        FavouriteNewborn::class,
        FavouriteIdCard::class,
        FavouriteDriversLicence::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newbornDao(): NewbornDao
    abstract fun idCardDao(): IdCardDao
    abstract fun driversLicenceDao(): DriversLicenceDao

    abstract fun favouriteNewbornDao(): FavouriteNewbornDao
    abstract fun favouriteIdCardDao(): FavouriteIdCardDao
    abstract fun favouriteDriversLicenceDao(): FavouriteDriversLicenceDao
}