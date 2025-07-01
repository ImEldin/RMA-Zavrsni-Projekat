package com.example.rma_zavrsni_projekat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_id_cards")
data class FavouriteIdCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val entity: String,
    val canton: String,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val issuedFirstTimeMaleTotal: Int,
    val replacedMaleTotal: Int,
    val issuedFirstTimeFemaleTotal: Int,
    val replacedFemaleTotal: Int,
    val total: Int
)
