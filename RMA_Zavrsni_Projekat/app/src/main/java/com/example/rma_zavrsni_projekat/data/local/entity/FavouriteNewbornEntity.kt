package com.example.rma_zavrsni_projekat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_newborns")
data class FavouriteNewborn(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val entity: String,
    val canton: String,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val maleTotal: Int,
    val femaleTotal: Int,
    val total: Int
)