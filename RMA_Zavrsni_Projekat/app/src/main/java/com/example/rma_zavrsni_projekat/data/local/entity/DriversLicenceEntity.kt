package com.example.rma_zavrsni_projekat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rma_zavrsni_projekat.data.model.DriversLicence

@Entity(tableName = "drivers_licences")
data class DriversLicenceEntity(
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

fun DriversLicenceEntity.toDriversLicence(): DriversLicence {
    return DriversLicence(
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

fun DriversLicence.toEntity(): DriversLicenceEntity {
    return DriversLicenceEntity(
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