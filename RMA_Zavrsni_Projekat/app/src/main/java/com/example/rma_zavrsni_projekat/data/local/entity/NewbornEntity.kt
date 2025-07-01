package com.example.rma_zavrsni_projekat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rma_zavrsni_projekat.data.model.Newborn

@Entity(tableName = "newborns")
data class NewbornEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

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

fun NewbornEntity.toNewborn(): Newborn {
    return Newborn(
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        maleTotal = maleTotal,
        femaleTotal = femaleTotal,
        total = total
    )
}

fun Newborn.toEntity(): NewbornEntity {
    return NewbornEntity(
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        maleTotal = maleTotal,
        femaleTotal = femaleTotal,
        total = total
    )
}