package com.example.rma_zavrsni_projekat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rma_zavrsni_projekat.data.model.IdCard

@Entity(tableName = "id_cards")
data class IdCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

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

fun IdCardEntity.toIdCard(): IdCard {
    return IdCard(
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        issuedFirstTimeMaleTotal = issuedFirstTimeMaleTotal,
        replacedMaleTotal = replacedMaleTotal,
        issuedFirstTimeFemaleTotal = issuedFirstTimeFemaleTotal,
        replacedFemaleTotal = replacedFemaleTotal,
        total = total
    )
}

fun IdCard.toEntity(): IdCardEntity {
    return IdCardEntity(
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        issuedFirstTimeMaleTotal = issuedFirstTimeMaleTotal,
        replacedMaleTotal = replacedMaleTotal,
        issuedFirstTimeFemaleTotal = issuedFirstTimeFemaleTotal,
        replacedFemaleTotal = replacedFemaleTotal,
        total = total
    )
}